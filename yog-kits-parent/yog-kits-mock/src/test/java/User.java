import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import lombok.Data;

/**
 * {@link User}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/14
 */
@Data
public class User {
  private String name;
  private Integer time;
  private CountryInfo countryInfo;
  private List<CountryInfo> countryInfoList;

  public static void main(String[] args) throws UnsupportedEncodingException {
    String ttt ="测试数据".concat(".xlsx");
    System.out.println(ttt);
  }
}
