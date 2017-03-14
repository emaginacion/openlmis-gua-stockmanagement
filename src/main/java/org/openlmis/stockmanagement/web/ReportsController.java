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

package org.openlmis.stockmanagement.web;

import org.openlmis.stockmanagement.service.JasperReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/api")
public class ReportsController {

  @Autowired
  private JasperReportService reportService;

  private static final Logger LOGGER = LoggerFactory.getLogger(ReportsController.class);

  /**
   * Get stock card report in PDF format.
   *
   * @param stockCardId stock card id.
   * @return generated PDF report
   */
  @RequestMapping(value = "/stockCards/{id}/print")
  @ResponseBody
  public ModelAndView getStockCard(@PathVariable("id") UUID stockCardId) {
    LOGGER.debug("Try to generate stock card report with id: " + stockCardId);

    return reportService.getStockCardReportView(stockCardId);
  }
}
