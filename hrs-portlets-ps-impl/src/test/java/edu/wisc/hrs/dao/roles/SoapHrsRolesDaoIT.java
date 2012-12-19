/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package edu.wisc.hrs.dao.roles;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.WebServiceFaultException;

import edu.wisc.hrs.dao.BaseSoapDaoTest;

/**
 * 
 * @version $Id: SoapHrsRolesDaoIT.java,v 1.1 2011/12/04 06:11:02 dalquist Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/integrationPeopleSoftTestContext.xml")
public class SoapHrsRolesDaoIT extends BaseSoapDaoTest {
    @Autowired
    protected SoapHrsRolesDao client;
    
    @Test
    public void testGetRoles() throws Exception {
        final Set<String> hrsRoles = client.getHrsRoles("00000015");
        verifyMappedData(hrsRoles);
    }
    
    @Test(expected=WebServiceFaultException.class)
    public void testGetMissingRoles() throws Exception {
        client.getHrsRoles("00000000");
    }

    protected void verifyMappedData(final Set<String> roles) {
        assertNotNull(roles);
        assertEquals(2, roles.size());
    }
}
