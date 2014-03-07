package ro.alex.browser.ui;

import ro.alex.client.proxy.ClasaProxy;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ClasaProperties extends PropertyAccess<ClasaProxy> {
	ModelKeyProvider<ClasaProxy> id();

	LabelProvider<ClasaProxy> clasa();
}