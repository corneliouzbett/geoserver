/* Copyright (c) 2001 - 2009 TOPP - www.openplans.org.  All rights reserved.
 * This code is licensed under the GPL 2.0 license, availible at the root
 * application directory.
 */
package org.geoserver.catalog.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.custommonkey.xmlunit.SimpleNamespaceContext;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.XpathEngine;
import org.geoserver.catalog.Catalog;
import org.geoserver.data.test.DefaultTestData;
import org.geoserver.security.AccessMode;
import org.geoserver.test.GeoServerIntegrationTestSupport;
import org.junit.Before;

public abstract class CatalogRESTTestSupport extends GeoServerIntegrationTestSupport<DefaultTestData> {

    protected static Catalog catalog;
    protected static XpathEngine xp;

    @Override
    protected void onSetUp(DefaultTestData testData) throws Exception {
        super.onSetUp(testData);

        //addUser("admin", "geoxserver", null, Arrays.asList("ROLE_ADMINISTRATOR"));
        addLayerAccessRule("*", "*", AccessMode.READ, "*");
        addLayerAccessRule("*", "*", AccessMode.WRITE, "*");

        catalog = getCatalog();
        
        Map<String, String> namespaces = new HashMap<String, String>();
        namespaces.put("html", "http://www.w3.org/1999/xhtml");
        namespaces.put("sld", "http://www.opengis.net/sld");
        namespaces.put("ogc", "http://www.opengis.net/ogc");
        namespaces.put("atom", "http://www.w3.org/2005/Atom");
        
        XMLUnit.setXpathNamespaceContext(new SimpleNamespaceContext(namespaces));
        xp = XMLUnit.newXpathEngine();
    }

    protected final void setUpUsers(Properties props) {
    }

    protected final void setUpLayerRoles(Properties properties) {
    }

    @Before
    public void login() throws Exception {
        login("admin", "geoserver", "ROLE_ADMINISTRATOR");
    }
}
