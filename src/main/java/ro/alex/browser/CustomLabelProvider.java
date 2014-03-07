package ro.alex.browser;

import ro.alex.client.proxy.ZborProxy;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.widget.core.client.info.Info;

public class CustomLabelProvider implements LabelProvider<ZborProxy>{

	@Override
	public java.lang.String getLabel(ZborProxy item) {
		String str = DateTimeFormat.getFormat("h:mm a").format(item.getData());
		Info.display("Alert", str);
		return str;
	}
}