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
package edu.wisc.hrs.dao.person;

import static junit.framework.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.WebServiceFaultException;

import edu.wisc.hr.dm.person.PersonInformation;
import edu.wisc.hrs.dao.BaseSoapDaoTest;

/**
 * 
 * @version $Id: SoapContactInfoDaoIT.java,v 1.1 2011/12/04 06:11:04 dalquist Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/integrationPeopleSoftTestContext.xml")
public class SoapContactInfoDaoIT extends BaseSoapDaoTest {
    @Autowired
    protected SoapContactInfoDao client;
    
    @Test
    public void testGetPersonInformation() throws Exception {
        final PersonInformation personInformation = client.getPersonalData("00481400");
        verifyMappedData(personInformation);
    }

    @Test(expected=WebServiceFaultException.class)
    public void testGetMissingPersonInformation() throws Exception {
        client.getPersonalData("00000000");
    }
    
    protected void verifyMappedData(final PersonInformation personInformation) {
        assertNotNull(personInformation);
    }
}

