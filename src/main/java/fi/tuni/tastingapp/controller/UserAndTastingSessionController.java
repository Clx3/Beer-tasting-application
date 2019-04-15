package fi.tuni.tastingapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.tastingapp.entity.UserAndTastingSession;
import fi.tuni.tastingapp.repository.UserAndTastingSessionRepository;

@RestController
public class UserAndTastingSessionController {
	
	@Autowired
	private UserAndTastingSessionRepository userAndTastingSessionRepository;
	
	@RequestMapping(value = "userandtastingsession/", method = RequestMethod.POST)
	public UserAndTastingSession joinTastingSession(@RequestBody UserAndTastingSession userAndTastingSession) {
		UserAndTastingSession request = userAndTastingSession;
		
		UserAndTastingSession current = userAndTastingSessionRepository.findByUserIdAndTastingSessionId(request.getUserId(), request.getTastingSessionId());
		
		if(current != null)
			return current;
		else
			return userAndTastingSessionRepository.save(request);
	}
	
	@RequestMapping(value = "userandtastingsession/user/{userId}", method = RequestMethod.GET)
	public List<Long> getUsersJoinedTastingSessions(@PathVariable long userId) {
		List<Long> tastingSessionIds = new ArrayList<>();
		
		userAndTastingSessionRepository.findTastingSessionIdsByUserId(userId)
		.stream().forEach(e -> tastingSessionIds.add(e.longValue()));
		
		return tastingSessionIds;
	}

	@RequestMapping(value = "userandtastingsession/users/{tastingSessionId}", method = RequestMethod.GET)
	public List<Long> getTastingSessionsUserIds(@PathVariable long tastingSessionId) {
		List<Long> userIds = new ArrayList<>();

		userAndTastingSessionRepository.getTastingSessionsUserIds(tastingSessionId)
				.stream().forEach(e -> userIds.add(e.longValue()));

		return userIds;
	}
	
	@RequestMapping(value = "userandtastingsession", method = RequestMethod.GET)
	public UserAndTastingSession getByUserIdAndTastingSessionId(@RequestParam long userId, @RequestParam long tastingSessionId) throws UserHasNotJoinedSessionException {
		UserAndTastingSession userAndTastingSession = userAndTastingSessionRepository.findByUserIdAndTastingSessionId(userId, tastingSessionId);
		
		if(userAndTastingSession != null)
			return userAndTastingSession;
		else
			throw new UserHasNotJoinedSessionException("User id: " + userId + " has not joined tasting session " + tastingSessionId + "!");
	}
	
	@RequestMapping(value = "userandtastingsession", method = RequestMethod.DELETE)
	void deleteUserFromTastingSession(@RequestParam long userId, @RequestParam long tastingSessionId) {
		UserAndTastingSession toBeDeleted = userAndTastingSessionRepository.findByUserIdAndTastingSessionId(userId, tastingSessionId);
		
		if(toBeDeleted != null)
			userAndTastingSessionRepository.delete(toBeDeleted);
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	class UserHasNotJoinedSessionException extends AuthenticationException {
		
		public UserHasNotJoinedSessionException(String msg) {
			super(msg);
		}
		
	}
	
}
