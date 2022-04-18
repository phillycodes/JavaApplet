import java.net.*;
import java.applet.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class TestSites extends JApplet {
	
	private HashMap<String, URL> websiteInfo;
	private ArrayList<String> websiteTitle;
	private JList mainList ;
	
	//init
	public void init() {
		websiteInfo = new HashMap<String, URL>() ;
		websiteTitle = new ArrayList<String>();
		
		grabHTMLInfo();
		add(new JLabel("Select a Website"), BorderLayout.NORTH);
		mainList = new JList(websiteTitle.toArray());
		
		mainList.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						Object object = mainList.getSelectedValue();
						URL newDoc = websiteInfo.get(object);
						AppletContext browser = getAppletContext();
						browser.showDocument(newDoc);
					}//end valueChanged
				}//end new object
		); //listSelctionListener
		add(new JScrollPane(mainList), BorderLayout.CENTER);
	} //end init
	
	//get website info
	private void grabHTMLInfo() {
		String title;
		String address;
		URL url;
		int counter = 0;
		title = getParameter("title" + counter);
		
		while(title != null) {
			address = getParameter("address" + counter);
			try {
				url = new URL(address);
				websiteInfo.put(title, url);
				websiteTitle.add(title);
			}catch(MalformedURLException urlEx) {
				urlEx.printStackTrace();
			}// close catch
			++counter;
			title = getParameter("title" + counter);
		}
	}

}
