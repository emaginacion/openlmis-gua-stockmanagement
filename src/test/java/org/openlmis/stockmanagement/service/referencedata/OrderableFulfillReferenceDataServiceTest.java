/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2017 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Affero General Public License for more details. You should have received a copy of
 * the GNU Affero General Public License along with this program. If not, see
 * http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
 */

package org.openlmis.stockmanagement.service.referencedata;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.openlmis.stockmanagement.dto.referencedata.OrderableFulfillDto;
import org.openlmis.stockmanagement.testutils.OrderableFulfillDtoDataBuilder;
import org.openlmis.stockmanagement.util.RequestParameters;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class OrderableFulfillReferenceDataServiceTest {

  @Mock
  private ObjectMapper objectMapper;

  @InjectMocks
  @Spy
  private OrderableFulfillReferenceDataService service;

  private UUID id = UUID.randomUUID();

  @Before
  public void setUp() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    TypeFactory factory = mapper.getTypeFactory();

    when(objectMapper.getTypeFactory()).thenReturn(factory);
  }

  @Test
  public void shouldReturnMapOfOrderableFulfills() {
    Map<UUID, OrderableFulfillDto> map = new HashMap<>();
    map.put(UUID.randomUUID(), new OrderableFulfillDtoDataBuilder().build());

    doReturn(map)
        .when(service).findOne(any(RequestParameters.class));
    when(objectMapper.convertValue(any(Object.class), any(JavaType.class)))
        .thenReturn(map);

    Map<UUID, OrderableFulfillDto> result = service.findByIds(Collections.singletonList(id));

    assertEquals(result, map);
  }
}
