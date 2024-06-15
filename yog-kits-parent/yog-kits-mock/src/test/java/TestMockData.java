import cn.hutool.json.JSONUtil;
import com.yogjun.kits.mock.MockData;

/**
 * {@link TestMockData}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/14
 */
public class TestMockData {
  public static void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    User us = MockData.fillObject(User.class);
    System.out.println(JSONUtil.toJsonStr(us));
  }
}
