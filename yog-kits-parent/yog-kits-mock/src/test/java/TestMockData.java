import cn.hutool.json.JSONUtil;
import com.yogjun.kits.mock.MockData;

/**
 * {@link TestMockData}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/14
 */
public class TestMockData {
//  public static void main(String[] args)
//      throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//    User us = MockData.fillObject(User.class);
//    System.out.println(JSONUtil.toJsonStr(us));
//  }

  public static void main(String[] args){
    String a1 = "12323";
    String a2= "12323abc";
    String a3= "abc";
    System.out.println(a1.matches("\\d+"));
    System.out.println(a2.matches("\\d+"));
    System.out.println(a3.matches("\\d+"));
  }
}
