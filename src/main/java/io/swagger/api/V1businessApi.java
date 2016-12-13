package io.swagger.api;

import io.swagger.model.Business01Return;
import io.swagger.model.SigninToken;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.solver.swagger.backend.consts.Consts;
import com.solver.swagger.backend.entity.PUAccessToken;
import com.solver.swagger.backend.entity.PUUser;

import io.swagger.annotations.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Path("/v1business")

@Api(description = "the v1business API")

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2016-12-12T05:16:01.773Z")

@Singleton
public class V1businessApi {

	private static Logger logger = Logger.getLogger(V1businessApi.class);

	@PersistenceContext(unitName = "SOLVER_COMPUTATIONAL_SERVICES")
	private EntityManager em;

	@OPTIONS
	@Path("/business01")
	public Response v1businessBusiness01Options() {
		System.out.println("v1businessBusiness01Options");
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET").header("Access-Control-Allow-Headers", "Content-Type, Authorization").build();
	}

	@GET
	@Path("/business01")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = Business01Return.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 response", response = Business01Return.class),
			@ApiResponse(code = 403, message = "403 response", response = Business01Return.class) })
	public Response v1businessBusiness01Get(@QueryParam("param01") String param01, @HeaderParam("Authorization") String authorization) {
		System.out.println("v1businessBusiness01Get [authorization:" + authorization + "] [param01:" + param01 + "]");
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			return Response.status(403).header("Access-Control-Allow-Origin", "*").build();
		}
		String accessToken = authorization.substring("Bearer".length()).trim();

		TypedQuery<PUAccessToken> queryDevice = em.createNamedQuery("AccessToken.FindByAccessToken", PUAccessToken.class);
		queryDevice.setParameter("accessToken", accessToken);
		PUAccessToken puAccessToken = null;
		try {
			puAccessToken = queryDevice.getSingleResult();

			Business01Return business01Return = new Business01Return();
			business01Return.setResponse01("value-" + param01);
			return Response.ok().header("Access-Control-Allow-Origin", "*").entity(business01Return).build();
		} catch (NoResultException nre) {
			logger.info("NoResultException_01 ");
			return Response.status(403).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
}
