package com.melexis;

import com.melexis.repository.ProductRepository;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.melexis.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{

	private ProductRepository productRepository;

	public WicketApplication()
	{
	}

	public void init() {
		super.init();
		addComponentInstantiationListener(new SpringComponentInjector(this));
	}
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

}
