package havis.net.rest.shared;

import havis.net.rest.shared.provider.URISyntaxExceptionMapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Providers;

public class NetApplication extends Application {

	private final Set<Object> singletons = new HashSet<Object>();
	{
		singletons.add(new NetService());
	}

	public Set<Object> getSingletons() {
		return singletons;
	}

	@Override
	public Map<String, Object> getProperties() {
		String PROVIDERS = Providers.class.getName();
		Map<String, Object> properties = new HashMap<>();
		properties.put(PROVIDERS, new Class<?>[] { URISyntaxExceptionMapper.class });
		return properties;
	}
}