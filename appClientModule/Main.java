import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
//import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//import org.json.simple.JSONObject;

public class Main {
	public static void main(String[] args) {
		String arg = args[0];
		String res = "";
		
		String urlString = "";
		String errorCode = "";
		String errorMessage = "";
		
		String data = "";
		String method = "";
		String id = "";
		
System.out.println("argument: " + arg);
		
		if (arg.equals("pi")) {
			urlString = "http://10.1.166.32/api/v2/projects";method = "POST";
			/*
			StringWriter out = new StringWriter();
			JSONObject obj=new JSONObject();
			obj.put("id",new Long(6));
			obj.put("name","KBank");
			try {
				obj.writeJSONString(out);
				res = out.toString();
			} catch (Exception e) {
				System.out.println("error: " + e.getMessage());
			}
			*/
			res = "{`id`:10,`name`:`KBank`}";
			res = res.replace('`', '"');
		} else if (arg.equals("ei")) {
			urlString = "http://10.1.166.32/api/v2/nodes";method = "POST";
			res = "{`id`:1192,`status`:null,`location`:111,`longitude`:`100.59`,`latitude`:`7.20`,`host_name`:`xx`,`equipment_id`:`SKA00423-A1192`,`ip`:`10.0.12.21`}";
			res = res.replace('`', '"');
		} else if (arg.equals("eq")) {
			urlString = "http://10.1.166.32/api/v2/nodes";method = "GET";
			res = "{`id`:1192,`status`:null,`location`:111,`longitude`:`100.59`,`latitude`:`7.20`,`host_name`:`xx`,`equipment_id`:`SKA00423-A1192`,`ip`:`10.0.12.21`}";
			res = res.replace('`', '"');
		} else {
			System.out.println("wrong argument");
		}
		data = res;

		String result = "No result";
		HttpURLConnection urlcon = null;
		DataOutputStream output = null;
		InputStream input = null;
		BufferedReader reader = null;
		String postdata = "";
		
		try {
		    postdata = data;
		    if (!method.equals("POST")) {urlString += "/" + id;}
		    URL url = new URL(urlString);
		    
System.out.println("url: " + urlString);
System.out.println("method: " + method);
System.out.println("data: " + postdata);
		    
		    urlcon = (HttpURLConnection)url.openConnection();
		    //urlcon.setRequestMethod("POST");
		    urlcon.setRequestMethod(method);
		    urlcon.setRequestProperty("Content-Type", "application/json;charset=" + "UTF-8");
		    urlcon.setUseCaches(false);
		    urlcon.setDoInput(true);
		    urlcon.setDoOutput(true);
		    
		    OutputStream os = urlcon.getOutputStream();
		    os.write(postdata.getBytes());

		    os.flush();
		    
		    input = urlcon.getInputStream();
		    reader = new BufferedReader(new InputStreamReader(input,"UTF-8"));
		    result = reader.readLine();
		    errorCode = "Notifier00";
		    errorMessage = "No error";
		} catch (MalformedURLException e) { 
		    errorCode = "Notifier03";
		    errorMessage = e.toString();
		} catch (IOException e) {
		    errorCode = "Notifier04";
		    errorMessage = e.toString();
		} finally {
		    if(reader!=null){try{reader.close();}catch(Exception e){errorCode="Notifier96";errorMessage=e.toString();}reader=null;}
		    if(output!=null){try{output.close();}catch(Exception e){errorCode="Notifier97";errorMessage=e.toString();}output=null;}
		    if(input!=null){try{input.close();}catch(Exception e){errorCode="Notifier98";errorMessage=e.toString();}input=null;}
		    if(urlcon!=null){try{urlcon.disconnect();}catch(Exception e){errorCode="Notifier99";errorMessage=e.toString();}urlcon=null;}
System.out.println("errorCode: " + errorCode);
System.out.println("errorMessage: " + errorMessage);
System.out.println("result: '" + result + "'");
		}
	}

	public Main() {
		super();
	}
}