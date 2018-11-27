package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Grade;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ndktools.javamd5.Mademd5;

public class getCredit extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String _password = request.getParameter("password");

		Mademd5 md = new Mademd5();
		String password = md.toMd5(_password).toLowerCase();

		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();

		Document preDoc = null;
		try {
			preDoc = Jsoup.connect("http://cas.hdu.edu.cn/cas/login")
					.timeout(10000).get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Element _ele = preDoc.select("input[name=lt]").first();
		// class绛変簬masthead鐨刣iv鏍囩
		String lt = _ele.attr("value");
		System.out.println(lt);

		HttpPost httpPost = new HttpPost("http://cas.hdu.edu.cn/cas/login");

		// 鍒涘缓鍙傛暟鍒楄〃
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		nvps.add(new BasicNameValuePair("service",
				"http://xgxt.hdu.edu.cn:80/neusoftcas.jsp"));
		nvps.add(new BasicNameValuePair("encodedService",
				"http%3a%2f%2fxgxt.hdu.edu.cn%3a80%2fneusoftcas.jsp"));
		nvps.add(new BasicNameValuePair("lt", lt));
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));
		nvps.add(new BasicNameValuePair("autoLogin", "true"));

		// 璁剧疆璇锋眰澶�
		httpPost.setHeader(
				"user-agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.62 Safari/537.36");

		// 鍚戞湇鍔″櫒鍙戦�乸ost璇锋眰
		try {
			// 璁剧疆httpPost鐨勫弬鏁�
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			CloseableHttpResponse myResponse = httpclient.execute(httpPost);
			HttpEntity _entity = myResponse.getEntity();
			String _content = EntityUtils.toString(_entity);
			Document LocationDoc = Jsoup.parse(_content);
			Element locationDom = LocationDoc.select("a[href]").first();
			String location = locationDom.attr("href");
			// System.out.println(_content);

			// 閲嶅畾鍚戯紝缁х画璇锋眰
			HttpGet httpGet2 = new HttpGet(location);
			CloseableHttpResponse response2 = httpclient.execute(httpGet2);
			HttpEntity _entity2 = response2.getEntity();
			String _content2 = EntityUtils.toString(_entity2);

			// System.out.println(_content2);

			// 澶勭悊鍥㈡敮涔︾櫥褰�
			if (_content2.indexOf("瑙掕壊閫夋嫨") != -1) {
				HttpPost httpPost2 = new HttpPost(
						"http://xgxt.hdu.edu.cn/login/manyxslogin");
				List<NameValuePair> nvps2 = new ArrayList<NameValuePair>();
				nvps2.add(new BasicNameValuePair("xstype", "istzs"));
				nvps2.add(new BasicNameValuePair("xsid", "28089"));

				httpPost2
						.setHeader(
								"user-agent",
								"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.62 Safari/537.36");

				httpPost2.setEntity(new UrlEncodedFormEntity(nvps2));
				CloseableHttpResponse response22 = httpclient
						.execute(httpPost2);
				HttpEntity _entity22 = response22.getEntity();
				String _content22 = EntityUtils.toString(_entity22);

				System.out.println("\nFFFFFFFUUUCCK\n");
				System.out.println(_content22);

			}

			String Jsession = null;
			List<Cookie> cookies = cookieStore.getCookies();
			for (int i = 0; i < cookies.size(); i++) {
				if (cookies.get(i).getName().equals("JSESSIONID")) {
					Jsession = cookies.get(i).getValue();
				}

			}

			Document doc = Jsoup
					.connect("http://xgxt.hdu.edu.cn/xuesheng/lookcj")
					.cookie("JSESSIONID", Jsession).timeout(10000).get();
			Element table = doc.select("table").first();
			Elements trs = table.select("tr");

			ArrayList<Grade> arr = new ArrayList<Grade>();
			for (int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				Elements tds = tr.select("td[align=center]");

				if (tds.size() > 5) {
					int num = 0;
					String[] temp = new String[6];
					for (int j = 0; j < tds.size(); j++) {
						Element td = tds.get(j);

						if (!td.hasAttr("rowspan")) {
							temp[num++] = td.text();
							System.out.println(td.text());

						}

					}
					Grade grade = new Grade(temp[0], temp[1], temp[2], temp[3],
							temp[4], temp[5]);
					arr.add(grade);

				}

			}

			request.getSession().setAttribute("gradeArr", arr);

			response.sendRedirect("/Credit/show.jsp");
			/*
			 * courseCreditDao ccd = new courseCreditDao(); for (int i = 0; i <
			 * arr.size(); i++) { Grade g = null; g = arr.get(i);
			 * System.out.println(g.getCourseNo() + g.getCourseName() +
			 * g.getCourseType() + g.getCourseCredit() + g.getFirstMark());
			 * String sql =
			 * "insert into allcredit(courseNo,courseName,courseType,courseCredit,firstMark)"
			 * + " values('" + g.getCourseNo() + "','" + g.getCourseName() +
			 * "','" + g.getCourseType() + "','" + g.getCourseCredit() + "','" +
			 * g.getFirstMark() + "');"; // System.out.println(sql); try {
			 * ccd.addCourseCredit(sql); } catch (Exception e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPost.abort();
		}

	}
}
