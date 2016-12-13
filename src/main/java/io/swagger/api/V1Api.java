package io.swagger.api;

import io.swagger.model.Empty;
import io.swagger.model.SigninToken;
import io.swagger.model.Signin;
import io.swagger.model.Signup;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.solver.swagger.backend.amazon.ses.AmazonSESUtils;
import com.solver.swagger.backend.consts.Consts;
import com.solver.swagger.backend.entity.PUAccessToken;
import com.solver.swagger.backend.entity.PUUser;

import io.swagger.annotations.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Path("/v1")

@Api(description = "the v1 API")

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2016-12-12T05:16:01.773Z")

@Singleton
public class V1Api {

	private static Logger logger = Logger.getLogger(V1Api.class);

	@PersistenceContext(unitName = "SOLVER_COMPUTATIONAL_SERVICES")
	private EntityManager em;

	@OPTIONS
	@Path("/emailverification")
	public Response v1EmailverificationOptions() {
		System.out.println("v1EmailverificationOptions");
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET").header("Access-Control-Allow-Headers", "Content-Type").build();
	}

	@GET
	@Path("/emailverification")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = Empty.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 response", response = Empty.class) })
	public Response v1EmailverificationGet(@QueryParam("code") String code) {
		TypedQuery<PUUser> queryDevice = em.createNamedQuery("User.findByVerificationCode", PUUser.class);
		queryDevice.setParameter("verificationCode", code);
		PUUser puUser = null;
		try {
			puUser = queryDevice.getSingleResult();
			puUser.setIsVerified(true);
//			return Response.ok().build();
			return Response.status(302).header("Access-Control-Allow-Origin", "*").header("Location", "http://localhost:8080/swagger-jaxrs-server-1.0.0/codeVerified.html").build();
		} catch (NoResultException nre) {
			logger.info("NoResultException_01 ");
			return Response.status(400).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@OPTIONS
	@Path("/signin")
	public Response v1SigninOptions() {
		System.out.println("v1SigninOptions");
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST").header("Access-Control-Allow-Headers", "Content-Type").build();
	}

	@POST
	@Path("/signin")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = SigninToken.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 response", response = SigninToken.class), @ApiResponse(code = 401, message = "401 response", response = SigninToken.class) })
	public Response v1SigninPost(Signin signin) {
		TypedQuery<PUUser> queryDevice = em.createNamedQuery("User.findByUsernamePassword", PUUser.class);
		queryDevice.setParameter("username", signin.getUsername());
		queryDevice.setParameter("password", signin.getPassword());
		PUUser puUser = null;
		try {
			String accessToken = UUID.randomUUID().toString();
			
			puUser = queryDevice.getSingleResult();
			if (!puUser.getIsVerified()) {
				logger.info("IsVerified_false_01 ");
				return Response.status(400).header("Access-Control-Allow-Origin", "*").build();
			}
			for (PUAccessToken puAccessToken : puUser.getAccessTokens()) {
				em.remove(puAccessToken);
			}
			PUAccessToken puAccessToken = new PUAccessToken();
			puAccessToken.setAccessToken(accessToken);
			puAccessToken.setAccessTokenExpirationDate(new Date().getTime() + new Date().getTimezoneOffset() * 60000 + Consts.ACCESS_TOKEN_PERIOD);
			puAccessToken.setCreationDate(new Date().getTime() + new Date().getTimezoneOffset() * 60000);
			puUser.setAccessTokens(new HashSet<PUAccessToken>());
			puUser.getAccessTokens().add(puAccessToken);
			em.persist(puAccessToken);

			SigninToken signinToken = new SigninToken();
			signinToken.setToken(accessToken);
			return Response.ok().header("Access-Control-Allow-Origin", "*").entity(signinToken).build();
		} catch (NoResultException nre) {
			logger.info("NoResultException_01 ");
			return Response.status(400).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@OPTIONS
	@Path("/signup")
	public Response v1SignupOptions() {
		System.out.println("v1SignupOptions");
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST").header("Access-Control-Allow-Headers", "Content-Type").build();
	}

	@POST
	@Path("/signup")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = Empty.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 response", response = Empty.class) })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Response v1SignupPost(Signup signup) {
		if (signup.getPassword1() == null || !signup.getPassword1().equals(signup.getPassword2())) {
			return Response.status(400).header("Access-Control-Allow-Origin", "*").build();
		}
		
		TypedQuery<PUUser> queryDevice = em.createNamedQuery("User.findByUsername", PUUser.class);
		queryDevice.setParameter("username", signup.getUsername());
		PUUser puUser = null;
		try {
			puUser = queryDevice.getSingleResult();
			return Response.status(403).header("Access-Control-Allow-Origin", "*").build();
		} catch (NoResultException nre) {
			logger.info("NoResultException_01 ");
			String code = UUID.randomUUID().toString();
			puUser = new PUUser();
			puUser.setUsername(signup.getUsername());
			puUser.setPassword(signup.getPassword1());
			puUser.setEmail(signup.getEmail());
			puUser.setVerificationCode(code);
			puUser.setIsVerified(false);
			em.persist(puUser);
			
			AmazonSESUtils.send(signup.getEmail(), code);
			
			return Response.ok().header("Access-Control-Allow-Origin", "*").build();
		}
	}
}
