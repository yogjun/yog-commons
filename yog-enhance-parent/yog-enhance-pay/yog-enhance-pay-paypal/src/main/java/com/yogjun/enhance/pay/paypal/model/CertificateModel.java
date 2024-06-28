package com.yogjun.enhance.pay.paypal.model;

import java.io.Serializable;
import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.Date;
import lombok.Data;

@Data
public class CertificateModel implements Serializable {

  private static final long serialVersionUID = -3066491891078735673L;

  /** 证书本身 */
  private X509Certificate itself;

  /** 版本号 */
  private int version;

  /** 证书序列号 */
  private String serialNumber;

  /** 签发者 */
  private Principal issuerDn;

  /** 主体名 */
  private Principal subjectDn;

  /** 有效起始日期 */
  private Date notBefore;

  /** 有效终止日期 */
  private Date notAfter;
}
