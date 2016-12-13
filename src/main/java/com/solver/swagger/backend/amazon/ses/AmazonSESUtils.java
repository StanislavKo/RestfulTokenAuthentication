package com.solver.swagger.backend.amazon.ses;

import java.io.IOException;
import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.*;

public class AmazonSESUtils {
	static final String FROM = "asdf45qwer2@gmail.com"; // Replace with your "From" address. This address must be verified.
	static final String BODY = "Please confirm your registration with code http://localhost:8080/swagger-jaxrs-server-1.0.0/pretest/v1/emailverification?code=";
	static final String SUBJECT = "Solver Computational Services registration";

	static final String ACCESS_KEY = "AKIAIHQAU7NKODFDAGGQ";
	static final String SECRET_KEY = "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";
	
	public static void send(String email, String code) {
		// Construct an object to contain the recipient address.
		Destination destination = new Destination().withToAddresses(new String[] { email });

		// Create the subject and body of the message.
		Content subject = new Content().withData(SUBJECT);
		Content textBody = new Content().withData(BODY + code);
		Body body = new Body().withText(textBody);

		// Create a message with the specified subject and body.
		Message message = new Message().withSubject(subject).withBody(body);

		// Assemble the email.
		SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

		try {
			System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

			// Instantiate an Amazon SES client, which will make the service call. The service call requires your AWS credentials. 
			// Because we're not providing an argument when instantiating the client, the SDK will attempt to find your AWS credentials 
			// using the default credential provider chain. The first place the chain looks for the credentials is in environment variables 
			// AWS_ACCESS_KEY_ID and AWS_SECRET_KEY. 
			// For more information, see http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/credentials.html
			AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
			AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

			// Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your sandbox 
			// status, sending limits, and Amazon SES identity-related settings are specific to a given AWS 
			// region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using 
			// the US West (Oregon) region. Examples of other regions that Amazon SES supports are US_EAST_1 
			// and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html 
			Region REGION = Region.getRegion(Regions.US_WEST_2);
			client.setRegion(REGION);

			// Send the email.
			client.sendEmail(request);
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		}
	}

}
