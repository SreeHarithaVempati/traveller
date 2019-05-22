package com.concretepage.serviceTest;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.concretepage.dao.RegisterDao;
import com.concretepage.entity.CustomerDetails;
import com.concretepage.entity.SignUpStatus;
import com.concretepage.repository.CusRepository;
import com.concretepage.service.RegisterLoginService;


public class LoginServiceTest {
	@Mock
	private RegisterDao registerDao;
	@Mock
	private CusRepository mockedUserRepository;

	@InjectMocks
	private RegisterLoginService userService;
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceTest.class);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

//	@Test
//	public void testForSuccessfulSignup() {
//		LOGGER.info("Testing for Successful Signup..");
//		SignUpStatus expectedStatus = new SignUpStatus();
//		expectedStatus.setStatus(true);
//		CustomerDetails user = new CustomerDetails("sree", "sree@gmail.com","female");
//		SignUpStatus actualStatus = userService.register(user);
//		LOGGER.debug("User details -> {}", user);
//		LOGGER.debug("Signup status -> {}", actualStatus);
//		Mockito.verify(registerDao).save(user);
//		assertEquals(true,expectedStatus.equals(actualStatus) );
//
//	}
//
//	@Test
//	public void testForExistingEmailSignup() {
//		LOGGER.info("Testing for Existing Email Signup..");
//		SignUpStatus expectedStatus = new SignUpStatus();
//		expectedStatus.setStatus(false);
//		expectedStatus.setError("Email already exists!");
//		CustomerDetails user = new CustomerDetails("sree", "sree@gmail.com","female");
//		//when(mockedUserRepository.existsBymailId(user.getMailId())).thenReturn(true);
//		SignUpStatus actualStatus = userService.register(user);
//		LOGGER.debug("User details -> {}", user);
//		LOGGER.debug("Actual Status -> {}", actualStatus);
//		assertEquals(false,expectedStatus.equals(actualStatus));
//
//	}
}
