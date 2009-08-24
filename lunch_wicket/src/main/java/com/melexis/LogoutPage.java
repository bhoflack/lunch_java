/*
 *  Copyright 2009 brh.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package com.melexis;

import org.apache.wicket.authentication.pages.SignOutPage;
import org.apache.wicket.markup.html.link.PageLink;

/**
 *
 * @author brh
 */
public class LogoutPage extends SignOutPage {
	
	public LogoutPage() {
		add(new PageLink("home", HomePage.class));
	}

}
