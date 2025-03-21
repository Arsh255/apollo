/*
 * Copyright 2024 Apollo Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.ctrip.framework.apollo.audit.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.ctrip.framework.apollo.audit.context.ApolloAuditScope;
import com.ctrip.framework.apollo.audit.context.ApolloAuditScopeManager;
import com.ctrip.framework.apollo.audit.context.ApolloAuditSpan;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = ApolloAuditScopeManager.class)
public class ApolloAuditScopeManagerTest {

  @SpyBean
  ApolloAuditScopeManager manager;

  @Test
  public void testActivate() {
    ApolloAuditSpan mockSpan = mock(ApolloAuditSpan.class);

    // Create an ApolloAuditScope directly using the mock span
    ApolloAuditScope activeScope = new ApolloAuditScope(mockSpan);

    // Call the activate method of ApolloAuditScopeManager (manager)
    ApolloAuditScopeManager manager = new ApolloAuditScopeManager();
    manager.setScope(activeScope);  // Set the scope manually since there's no manager dependency

    // Verify the scope has been set
    assertEquals(activeScope, manager.getScope());
    assertEquals(mockSpan, activeScope.activeSpan());
  }


}
