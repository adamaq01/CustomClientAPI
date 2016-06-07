package fr.adamaq01.customclientapi.network.auth;

import java.io.IOException;

import fr.adamaq01.customclientapi.api.ProxyData;

public interface AuthService<T extends Session> {
	public T login(String username, String password) throws AuthenticationException, IOException;

	public T login(String username, String password, ProxyData proxy) throws AuthenticationException, IOException;

	public void authenticate(T session, String serverId) throws AuthenticationException, IOException;

	public void authenticate(T session, String serverId, ProxyData proxy) throws AuthenticationException, IOException;

	public T validateSession(Session session) throws InvalidSessionException;
}
