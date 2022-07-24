package com.utp.registro_de_productos.provider;

import com.utp.registro_de_productos.core.Query;
import com.utp.registro_de_productos.model.ProductModel;
import com.utp.registro_de_productos.model.SupplierModel;
import com.utp.registro_de_productos.model.SupplierNotificationModel;
import com.utp.registro_de_productos.model.SupplierNotificationProductModel;
import io.vavr.control.Either;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupplierNotificationProvider {

    Query query = new Query();

    public Either<String, ArrayList<SupplierNotificationModel>> getSupplierNotifications() {
        ArrayList<SupplierNotificationModel> supplierNotification = new ArrayList<>();
        try {
            ResultSet rs = query.query("""
                select sn.id as sn_id,
                       sn.date as sn_date,
                       sn.supplierId as sn_supplierId,
                       s.id as s_id,
                       s.name as s_name,
                       s.ruc as s_ruc,
                       s.phone as s_phone,
                       s.email as s_email
                from supplier_notification sn
                         inner join supplier s on sn.supplierId = s.id;
                                       """);
            while (rs.next()) {
                SupplierNotificationModel supplierNotificationModel = new SupplierNotificationModel();

                supplierNotificationModel.setId(rs.getString("sn_id"));
                supplierNotificationModel.setDate(rs.getDate("sn_date"));
                supplierNotificationModel.setSupplier(new SupplierModel(rs.getString("s_id"), rs.getString("s_name"), rs.getString("s_ruc"), rs.getString("s_phone"), rs.getString("s_email")));

                ArrayList<SupplierNotificationProductModel> supplierNotificationProducts = new ArrayList<>();

                ResultSet rs2 = query.query(""
                        + "select "
                        + "    snp.productId as snp_productId, "
                        + "    snp.quantity as snp_quantity, "
                        + "    snp.supplierNotificationId as snp_supplierNotificationId, "
                        + "    p.name as p_name, "
                        + "    p.description as p_description, "
                        + "    p.price as p_price, "
                        + "    p.quantity as p_quantity, "
                        + "    p.categoryId as p_categoryId, "
                        + "    p.supplierId as p_supplierId "
                        + "from `supplier_notification-product` as snp "
                        + "inner join product p on snp.productId = p.id "
                        + "where snp.supplierNotificationId = '" + supplierNotificationModel.getId() +"';");

                while(rs2.next()) {
                    SupplierNotificationProductModel supplierNotificationProduct = new SupplierNotificationProductModel();
                    
                    supplierNotificationProduct.setQuantity(rs2.getInt("snp_quantity"));
                    ProductModel product = new ProductModel();
                    product.setId(rs2.getString("snp_productId"));
                    product.setName(rs2.getString("p_name"));
                    product.setDescription(rs2.getString("p_description"));
                    product.setPrice(rs2.getDouble("p_price"));
                    product.setQuantity(rs2.getInt("p_quantity"));
                    supplierNotificationProduct.setProduct(product);
                    supplierNotificationProducts.add(supplierNotificationProduct);
                }
                supplierNotificationModel.setSupplierNotificationProducts(supplierNotificationProducts);
                supplierNotification.add(supplierNotificationModel);
            }
            return Either.right(supplierNotification);
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

    public Either<String, String> sendEmail(SupplierNotificationModel supplierNotification) throws Exception {
        insertSupplierNotification(supplierNotification);

        String supplierEmail = supplierNotification.getSupplier().getEmail();
        String products = "";

        for (SupplierNotificationProductModel supplierNotificationProduct : supplierNotification.getSupplierNotificationProducts()) {
            products += createProductTemplate(supplierNotificationProduct);
        }

        String body = """
                                    <!DOCTYPE html
                                      PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                                    <html xmlns="http://www.w3.org/1999/xhtml" xmlns:o="urn:schemas-microsoft-com:office:office">
                                    
                                    <head>
                                      <meta charset="UTF-8">
                                      <meta content="width=device-width, initial-scale=1" name="viewport">
                                      <meta name="x-apple-disable-message-reformatting">
                                      <meta http-equiv="X-UA-Compatible" content="IE=edge">
                                      <meta content="telephone=no" name="format-detection">
                                      <title></title>
                                      <!--[if (mso 16)]>
                                        <style type="text/css">
                                        a {text-decoration: none;}
                                        </style>
                                        <![endif]-->
                                      <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->
                                      <!--[if gte mso 9]>
                                    <xml>
                                        <o:OfficeDocumentSettings>
                                        <o:AllowPNG></o:AllowPNG>
                                        <o:PixelsPerInch>96</o:PixelsPerInch>
                                        </o:OfficeDocumentSettings>
                                    </xml>
                                    <![endif]-->
                                      <style type="text/css">
                                        #outlook a {
                                          padding: 0;
                                        }
                                    
                                        .es-button {
                                          mso-style-priority: 100 !important;
                                          text-decoration: none !important;
                                        }
                                    
                                        a[x-apple-data-detectors] {
                                          color: inherit !important;
                                          text-decoration: none !important;
                                          font-size: inherit !important;
                                          font-family: inherit !important;
                                          font-weight: inherit !important;
                                          line-height: inherit !important;
                                        }
                                    
                                        .es-desk-hidden {
                                          display: none;
                                          float: left;
                                          overflow: hidden;
                                          width: 0;
                                          max-height: 0;
                                          line-height: 0;
                                          mso-hide: all;
                                        }
                                    
                                        [data-ogsb] .es-button {
                                          border-width: 0 !important;
                                          padding: 15px 30px 15px 30px !important;
                                        }
                                    
                                        @media only screen and (max-width:600px) {
                                    
                                          p,
                                          ul li,
                                          ol li,
                                          a {
                                            line-height: 150% !important
                                          }
                                    
                                          h1,
                                          h2,
                                          h3,
                                          h1 a,
                                          h2 a,
                                          h3 a {
                                            line-height: 120% !important
                                          }
                                    
                                          h1 {
                                            font-size: 28px !important;
                                            text-align: left
                                          }
                                    
                                          h2 {
                                            font-size: 24px !important;
                                            text-align: left
                                          }
                                    
                                          h3 {
                                            font-size: 20px !important;
                                            text-align: left
                                          }
                                    
                                          .es-header-body h1 a,
                                          .es-content-body h1 a,
                                          .es-footer-body h1 a {
                                            font-size: 28px !important;
                                            text-align: left
                                          }
                                    
                                          .es-header-body h2 a,
                                          .es-content-body h2 a,
                                          .es-footer-body h2 a {
                                            font-size: 24px !important;
                                            text-align: left
                                          }
                                    
                                          .es-header-body h3 a,
                                          .es-content-body h3 a,
                                          .es-footer-body h3 a {
                                            font-size: 20px !important;
                                            text-align: left
                                          }
                                    
                                          .es-menu td a {
                                            font-size: 12px !important
                                          }
                                    
                                          .es-header-body p,
                                          .es-header-body ul li,
                                          .es-header-body ol li,
                                          .es-header-body a {
                                            font-size: 14px !important
                                          }
                                    
                                          .es-content-body p,
                                          .es-content-body ul li,
                                          .es-content-body ol li,
                                          .es-content-body a {
                                            font-size: 14px !important
                                          }
                                    
                                          .es-footer-body p,
                                          .es-footer-body ul li,
                                          .es-footer-body ol li,
                                          .es-footer-body a {
                                            font-size: 12px !important
                                          }
                                    
                                          .es-infoblock p,
                                          .es-infoblock ul li,
                                          .es-infoblock ol li,
                                          .es-infoblock a {
                                            font-size: 12px !important
                                          }
                                    
                                          *[class="gmail-fix"] {
                                            display: none !important
                                          }
                                    
                                          .es-m-txt-c,
                                          .es-m-txt-c h1,
                                          .es-m-txt-c h2,
                                          .es-m-txt-c h3 {
                                            text-align: center !important
                                          }
                                    
                                          .es-m-txt-r,
                                          .es-m-txt-r h1,
                                          .es-m-txt-r h2,
                                          .es-m-txt-r h3 {
                                            text-align: right !important
                                          }
                                    
                                          .es-m-txt-l,
                                          .es-m-txt-l h1,
                                          .es-m-txt-l h2,
                                          .es-m-txt-l h3 {
                                            text-align: left !important
                                          }
                                    
                                          .es-m-txt-r img,
                                          .es-m-txt-c img,
                                          .es-m-txt-l img {
                                            display: inline !important
                                          }
                                    
                                          .es-button-border {
                                            display: block !important
                                          }
                                    
                                          a.es-button,
                                          button.es-button {
                                            font-size: 18px !important;
                                            display: block !important;
                                            border-right-width: 0px !important;
                                            border-left-width: 0px !important
                                          }
                                    
                                          .es-adaptive table,
                                          .es-left,
                                          .es-right {
                                            width: 100% !important
                                          }
                                    
                                          .es-content table,
                                          .es-header table,
                                          .es-footer table,
                                          .es-content,
                                          .es-footer,
                                          .es-header {
                                            width: 100% !important;
                                            max-width: 600px !important
                                          }
                                    
                                          .es-adapt-td {
                                            display: block !important;
                                            width: 100% !important
                                          }
                                    
                                          .adapt-img {
                                            width: 100% !important;
                                            height: auto !important
                                          }
                                    
                                          .es-m-p0 {
                                            padding: 0 !important
                                          }
                                    
                                          .es-m-p0r {
                                            padding-right: 0 !important
                                          }
                                    
                                          .es-m-p0l {
                                            padding-left: 0 !important
                                          }
                                    
                                          .es-m-p0t {
                                            padding-top: 0 !important
                                          }
                                    
                                          .es-m-p0b {
                                            padding-bottom: 0 !important
                                          }
                                    
                                          .es-m-p20b {
                                            padding-bottom: 20px !important
                                          }
                                    
                                          .es-mobile-hidden,
                                          .es-hidden {
                                            display: none !important
                                          }
                                    
                                          tr.es-desk-hidden,
                                          td.es-desk-hidden,
                                          table.es-desk-hidden {
                                            width: auto !important;
                                            overflow: visible !important;
                                            float: none !important;
                                            max-height: inherit !important;
                                            line-height: inherit !important
                                          }
                                    
                                          tr.es-desk-hidden {
                                            display: table-row !important
                                          }
                                    
                                          table.es-desk-hidden {
                                            display: table !important
                                          }
                                    
                                          td.es-desk-menu-hidden {
                                            display: table-cell !important
                                          }
                                    
                                          .es-menu td {
                                            width: 1% !important
                                          }
                                    
                                          table.es-table-not-adapt,
                                          .esd-block-html table {
                                            width: auto !important
                                          }
                                    
                                          table.es-social {
                                            display: inline-block !important
                                          }
                                    
                                          table.es-social td {
                                            display: inline-block !important
                                          }
                                    
                                          .es-m-p5 {
                                            padding: 5px !important
                                          }
                                    
                                          .es-m-p5t {
                                            padding-top: 5px !important
                                          }
                                    
                                          .es-m-p5b {
                                            padding-bottom: 5px !important
                                          }
                                    
                                          .es-m-p5r {
                                            padding-right: 5px !important
                                          }
                                    
                                          .es-m-p5l {
                                            padding-left: 5px !important
                                          }
                                    
                                          .es-m-p10 {
                                            padding: 10px !important
                                          }
                                    
                                          .es-m-p10t {
                                            padding-top: 10px !important
                                          }
                                    
                                          .es-m-p10b {
                                            padding-bottom: 10px !important
                                          }
                                    
                                          .es-m-p10r {
                                            padding-right: 10px !important
                                          }
                                    
                                          .es-m-p10l {
                                            padding-left: 10px !important
                                          }
                                    
                                          .es-m-p15 {
                                            padding: 15px !important
                                          }
                                    
                                          .es-m-p15t {
                                            padding-top: 15px !important
                                          }
                                    
                                          .es-m-p15b {
                                            padding-bottom: 15px !important
                                          }
                                    
                                          .es-m-p15r {
                                            padding-right: 15px !important
                                          }
                                    
                                          .es-m-p15l {
                                            padding-left: 15px !important
                                          }
                                    
                                          .es-m-p20 {
                                            padding: 20px !important
                                          }
                                    
                                          .es-m-p20t {
                                            padding-top: 20px !important
                                          }
                                    
                                          .es-m-p20r {
                                            padding-right: 20px !important
                                          }
                                    
                                          .es-m-p20l {
                                            padding-left: 20px !important
                                          }
                                    
                                          .es-m-p25 {
                                            padding: 25px !important
                                          }
                                    
                                          .es-m-p25t {
                                            padding-top: 25px !important
                                          }
                                    
                                          .es-m-p25b {
                                            padding-bottom: 25px !important
                                          }
                                    
                                          .es-m-p25r {
                                            padding-right: 25px !important
                                          }
                                    
                                          .es-m-p25l {
                                            padding-left: 25px !important
                                          }
                                    
                                          .es-m-p30 {
                                            padding: 30px !important
                                          }
                                    
                                          .es-m-p30t {
                                            padding-top: 30px !important
                                          }
                                    
                                          .es-m-p30b {
                                            padding-bottom: 30px !important
                                          }
                                    
                                          .es-m-p30r {
                                            padding-right: 30px !important
                                          }
                                    
                                          .es-m-p30l {
                                            padding-left: 30px !important
                                          }
                                    
                                          .es-m-p35 {
                                            padding: 35px !important
                                          }
                                    
                                          .es-m-p35t {
                                            padding-top: 35px !important
                                          }
                                    
                                          .es-m-p35b {
                                            padding-bottom: 35px !important
                                          }
                                    
                                          .es-m-p35r {
                                            padding-right: 35px !important
                                          }
                                    
                                          .es-m-p35l {
                                            padding-left: 35px !important
                                          }
                                    
                                          .es-m-p40 {
                                            padding: 40px !important
                                          }
                                    
                                          .es-m-p40t {
                                            padding-top: 40px !important
                                          }
                                    
                                          .es-m-p40b {
                                            padding-bottom: 40px !important
                                          }
                                    
                                          .es-m-p40r {
                                            padding-right: 40px !important
                                          }
                                    
                                          .es-m-p40l {
                                            padding-left: 40px !important
                                          }
                                    
                                          .es-m-margin {
                                            padding-left: 5px !important;
                                            padding-right: 5px !important;
                                            padding-top: 5px !important;
                                            padding-bottom: 5px !important
                                          }
                                        }
                                      </style>
                                    </head>
                                    
                                    <body
                                      style="width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0">
                                      <div class="es-wrapper-color" style="background-color:rgb(255, 255, 255)">
                                        <!--[if gte mso 9]>
                                    			<v:background xmlns:v="urn:schemas-microsoft-com:vml" fill="t">
                                    				<v:fill type="tile" color="{common__wrapperBackgroundColor}"></v:fill>
                                    			</v:background>
                                    		<![endif]-->
                                        <table class="es-wrapper" width="100%" cellspacing="0" cellpadding="0"
                                          style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:rgb(255, 255, 255)">
                                          <tbody>
                                            <tr>
                                              <td class="esd-email-paddings" valign="top" style="padding:0;Margin:0">
                                                <table cellpadding="0" cellspacing="0" class="es-header esd-header-popover" align="center"
                                                  style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top">
                                                  <tbody>
                                                    <tr>
                                                      <td class="esd-stripe" align="center" esd-custom-block-id="654725" style="padding:0;Margin:0">
                                                        <table class="es-header-body" align="center" cellpadding="0" cellspacing="0"
                                                          style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px">
                                                          <tbody>
                                                            <tr>
                                                              <td class="esd-structure es-p15t es-p15b es-p20r es-p20l" align="left"
                                                                style="Margin:0;padding-top:15px;padding-bottom:15px;padding-left:20px;padding-right:20px">
                                                                <!--[if mso]><table style="width:560px" cellpadding="0" cellspacing="0"><tr><td style="width:270px" valign="top"><![endif]-->
                                                                <table class="es-left" cellspacing="0" cellpadding="0" align="left"
                                                                  style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left">
                                                                  <tbody>
                                                                    <tr>
                                                                      <td class="esd-container-frame" align="left" style="padding:0;Margin:0;width:270px">
                                                                        <table width="100%" cellspacing="0" cellpadding="0"
                                                                          style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                                          <tbody>
                                                                            <tr>
                                                                              <td class="esd-block-image es-p5t es-m-p10t es-m-p10b es-m-txt-c" align="left"
                                                                                style="padding:0;Margin:0;padding-top:5px;font-size:0px"> <a target="_blank"
                                                                                  href="https://stripo.email/"
                                                                                  style="-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:rgb(51, 138, 204);font-size:14px"><img
                                                                                    src="https://res.cloudinary.com/dqs6spa0k/image/upload/v1657085624/vaca_g5t1wc.png"
                                                                                    alt="La Vaca"
                                                                                    style="display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic"
                                                                                    width="50" title="La Vaca"></a> </td>
                                                                            </tr>
                                                                          </tbody>
                                                                        </table>
                                                                      </td>
                                                                    </tr>
                                                                  </tbody>
                                                                </table>
                                                                <!--[if mso]></td><td style="width:20px"></td><td style="width:270px" valign="top"><![endif]-->
                                                                <table class="es-right" cellspacing="0" cellpadding="0" align="right"
                                                                  style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right">
                                                                  <tbody>
                                                                    <tr class="es-mobile-hidden">
                                                                      <td class="esd-container-frame" align="left" style="padding:0;Margin:0;width:270px">
                                                                        <table width="100%" cellspacing="0" cellpadding="0"
                                                                          style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                                          <tbody>
                                                                            <tr>
                                                                              <td class="esd-block-text es-m-txt-c" align="right"
                                                                                style="padding:0;Margin:0">
                                                                                <p
                                                                                  style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:rgb(125, 125, 125);font-size:14px">
                                                                                  <a target="_blank" href="mailto:u18104155@utp.edu.pe"
                                                                                    style="-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:underline;color:rgb(51, 138, 204);font-size:14px">u18104155@utp.edu.pe</a>
                                                                                </p>
                                                                              </td>
                                                                            </tr>
                                                                          </tbody>
                                                                        </table>
                                                                      </td>
                                                                    </tr>
                                                                  </tbody>
                                                                </table>
                                                                <!--[if mso]></td></tr></table><![endif]-->
                                                              </td>
                                                            </tr>
                                                          </tbody>
                                                        </table>
                                                      </td>
                                                    </tr>
                                                  </tbody>
                                                </table>
                                                <table cellpadding="0" cellspacing="0" class="es-content" align="center"
                                                  style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%">
                                                  <tbody>
                                                    <tr>
                                                      <td class="esd-stripe" align="center" style="padding:0;Margin:0">
                                                        <table bgcolor="#ffffff" class="es-content-body" align="center" cellpadding="0" cellspacing="0"
                                                          style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:rgb(255, 255, 255);width:600px">
                                                          <tbody>
                                                            <tr>
                                                              <td class="es-p20t es-p30r es-p30l esd-structure" align="left"
                                                                style="padding:0;Margin:0;padding-top:20px;padding-left:30px;padding-right:30px">
                                                                <table cellpadding="0" cellspacing="0" width="100%"
                                                                  style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                                  <tbody>
                                                                    <tr>
                                                                      <td class="esd-container-frame" align="center" valign="top"
                                                                        style="padding:0;Margin:0;width:540px">
                                                                        <table cellpadding="0" cellspacing="0" width="100%"
                                                                          style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                                          <tbody>
                                                                            <tr>
                                                                              <td class="esd-block-text es-p15t es-p15b" align="center"
                                                                                style="padding:0;Margin:0;padding-top:15px;padding-bottom:15px">
                                                                                <h1
                                                                                  style="Margin:0;line-height:53px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:44px;font-style:normal;font-weight:bold;color:rgb(125, 125, 125)">
                                                                                  Notificacion de productos</h1>
                                                                              </td>
                                                                            </tr>
                                                                            <tr>
                                                                              <td class="esd-block-text es-m-txt-l es-p20t es-p20b" bgcolor="#ffffff"
                                                                                align="left"
                                                                                style="padding:0;Margin:0;padding-top:20px;padding-bottom:20px">
                                                                                <p
                                                                                  style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:rgb(125, 125, 125);font-size:14px">
                                                                                  Estimados   """ + " " + supplierNotification.getSupplier().getName() + """
                                                                                                 ,<br><br>Adjunto el listado de productos
                                                                                  requeridos.</p>
                                                                              </td>
                                                                            </tr>
                                                                          </tbody>
                                                                        </table>
                                                                      </td>
                                                                    </tr>
                                                                  </tbody>
                                                                </table>
                                                              </td>
                                                            </tr>
                                                            """ + products + """
                                                          </tbody>
                                                        </table>
                                                      </td>
                                                    </tr>
                                                  </tbody>
                                                </table>
                                                <table cellpadding="0" cellspacing="0" class="es-footer" align="center"
                                                  style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top">
                                                  <tbody>
                                                    <tr>
                                                      <td class="esd-stripe" align="center" esd-custom-block-id="654699" style="padding:0;Margin:0">
                                                        <table class="es-footer-body" align="center" cellpadding="0" cellspacing="0"
                                                          style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:rgb(51, 51, 51);width:600px">
                                                          <tbody>
                                                            <tr>
                                                              <td class="esd-structure es-p15t es-p15b es-p20r es-p20l" align="left"
                                                                style="Margin:0;padding-top:15px;padding-bottom:15px;padding-left:20px;padding-right:20px">
                                                                <table cellpadding="0" cellspacing="0" width="100%"
                                                                  style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                                  <tbody>
                                                                    <tr>
                                                                      <td class="esd-container-frame" align="center" valign="top"
                                                                        style="padding:0;Margin:0;width:560px">
                                                                        <table cellpadding="0" cellspacing="0" width="100%"
                                                                          style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                                          <tbody>
                                                                            <tr>
                                                                              <td align="center" class="esd-block-text es-p10t es-p10b"
                                                                                style="padding:0;Margin:0;padding-top:10px;padding-bottom:10px">
                                                                                <p
                                                                                  style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:18px;color:rgb(125, 125, 125);font-size:12px">
                                                                                  <em>Copyright&nbsp;© 2022 La Vaca. All rights reserved.</em>
                                                                                </p>
                                                                              </td>
                                                                            </tr>
                                                                          </tbody>
                                                                        </table>
                                                                      </td>
                                                                    </tr>
                                                                  </tbody>
                                                                </table>
                                                              </td>
                                                            </tr>
                                                          </tbody>
                                                        </table>
                                                      </td>
                                                    </tr>
                                                  </tbody>
                                                </table>
                                              </td>
                                            </tr>
                                          </tbody>
                                        </table>
                                      </div>
                                    </body>
                                    
                                    </html>
                                    """;
        return EmailProvider.sendEmail(supplierEmail, body, "La Vaca - Notificación de productos");
    }

    private String createProductTemplate(SupplierNotificationProductModel supplierNotificationProduct) {
        String body = """
           <tr>
                                    <td class="esd-structure es-p10t es-p10b es-p30r es-p30l esdev-adapt-off" align="left"
                                      style="Margin:0;padding-top:10px;padding-bottom:10px;padding-left:30px;padding-right:30px">
                                      <table cellpadding="0" cellspacing="0" class="esdev-mso-table"
                                        style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:540px">
                                        <tbody>
                                          <tr>
                                            <td class="esdev-mso-td" valign="top" style="padding:0;Margin:0">
                                              <table cellpadding="0" cellspacing="0" class="es-left" align="left"
                                                style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left">
                                                <tbody>
                                                  <tr>
                                                    <td class="esd-container-frame" align="center"
                                                      style="padding:0;Margin:0;width:261px">
                                                      <table cellpadding="0" cellspacing="0" width="100%"
                                                        style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                        <tbody>
                                                          <tr>
                                                            <td align="left" class="esd-block-text" style="padding:0;Margin:0">
                                                              <p
                                                                style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:rgb(125, 125, 125);font-size:14px">
                                                                <strong>""" + supplierNotificationProduct.getProduct().getName() + """
                                                                                                                                 </strong>
                                                              </p>
                                                            </td>
                                                          </tr>
                                                        </tbody>
                                                      </table>
                                                    </td>
                                                  </tr>
                                                </tbody>
                                              </table>
                                            </td>
                                            <td style="padding:0;Margin:0;width:20px"></td>
                                            <td class="esdev-mso-td" valign="top" style="padding:0;Margin:0">
                                              <table cellpadding="0" cellspacing="0" class="es-right" align="right"
                                                style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right">
                                                <tbody>
                                                  <tr>
                                                    <td align="center" class="esd-container-frame"
                                                      style="padding:0;Margin:0;width:150px">
                                                      <table cellpadding="0" cellspacing="0" width="100%"
                                                        style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                                        <tbody>
                                                          <tr>
                                                            <td align="right" class="esd-block-text" style="padding:0;Margin:0">
                                                              <p
                                                                style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:rgb(125, 125, 125);font-size:14px">
                                                                """ + supplierNotificationProduct.getQuantity().toString() + """
                                                                    UNIDADES</p>
                                                            </td>
                                                          </tr>
                                                        </tbody>
                                                      </table>
                                                    </td>
                                                  </tr>
                                                </tbody>
                                              </table>
                                            </td>
                                          </tr>
                                        </tbody>
                                      </table>
                                    </td>
                                  </tr>
           """;
        return body;
    }

    public Either<String, String> insertSupplierNotification(SupplierNotificationModel supplierNotification) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String formatedDate = formatter.format(supplierNotification.getDate());
            int result = query.update(""
                    + "insert into supplier_notification (id, supplierId, date) values ('" + supplierNotification.getId() + "', '" + supplierNotification.getSupplier().getID() + "', '" + formatedDate + "');");

            supplierNotification.getSupplierNotificationProducts().forEach(product -> {
                try {
                    int result2 = query.update("insert into `supplier_notification-product` (productId, quantity, supplierNotificationId) values ("
                            + "'" + product.getProduct().getId() + "', " + product.getQuantity() + ", '" + supplierNotification.getId() + "');");
                } catch (Exception ex) {
                    Logger.getLogger(SupplierNotificationProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            return switch (result) {
                case 1 ->
                    Either.right("Se registró correctamente");
                case 0 ->
                    Either.left("No se registró");
                default ->
                    Either.left("Ocurrió un error");
            };
        } catch (Exception e) {
            System.out.println(e);
            return Either.left("Ocurrió un error");
        }
    }

}
