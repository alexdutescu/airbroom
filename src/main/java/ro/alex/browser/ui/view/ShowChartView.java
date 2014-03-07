package ro.alex.browser.ui.view;

import ro.alex.client.proxy.AbonamentProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.axis.GaugeAxis;
import com.sencha.gxt.chart.client.chart.series.GaugeSeries;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.fx.client.easing.EasingFunction;
import com.sencha.gxt.fx.client.easing.ElasticIn;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class ShowChartView {
	
	private AbonamentProxy abonamentProxy;
	private Chart<AbonamentProxy> chart;

	public interface AbonamentProxyPropertyAccess extends PropertyAccess<AbonamentProxy> {
		ValueProvider<AbonamentProxy, Integer> kilometri();

		ValueProvider<AbonamentProxy, String> serie();

		@Path("serie")
		ModelKeyProvider<AbonamentProxy> serieKey();
	}
	
	public ShowChartView(AbonamentProxy abonamentProxy){
		this.abonamentProxy = abonamentProxy;
		
		final ListStore<AbonamentProxy> store = new ListStore<AbonamentProxy>(AbonamentProxyAccess.serieKey());
		store.add(abonamentProxy);
		this.chart = createGauge(store, 80, new RGB("#3AA8CB"), false, new ElasticIn(), AbonamentProxyAccess.kilometri());
		
		ContentPanel panel = new FramedPanel();
	    panel.getElement().getStyle().setMargin(10, Unit.PX);
	    panel.setCollapsible(true);
	    panel.setHeadingText("Gauge Chart");
	    panel.setPixelSize(420, 700);
	    panel.setBodyBorder(true);
	 
	    VerticalLayoutContainer layout = new VerticalLayoutContainer();
	    panel.add(layout);
	    
	    chart.setLayoutData(new VerticalLayoutData(400, 200));
	    layout.add(chart);
	}
	
	private static final AbonamentProxyPropertyAccess AbonamentProxyAccess = GWT.create(AbonamentProxyPropertyAccess.class);
	
	private Chart<AbonamentProxy> createGauge(ListStore<AbonamentProxy> store, double donut, Color color, boolean needle,
		    EasingFunction easing, ValueProvider<AbonamentProxy, Integer> provider) {
		    Chart<AbonamentProxy> chart = new Chart<AbonamentProxy>();
		    chart.setStore(store);
		    chart.setAnimationDuration(750);
		    chart.setAnimationEasing(easing);
		    chart.setDefaultInsets(20);
		 
		    GaugeAxis<AbonamentProxy> axis = new GaugeAxis<AbonamentProxy>();
		    axis.setMargin(8);
		    axis.setDisplayGrid(true);
		    axis.setMinimum(0);
		    axis.setMaximum(2000);
		    chart.addAxis(axis);
		 
		    final GaugeSeries<AbonamentProxy> gauge = new GaugeSeries<AbonamentProxy>();
		    gauge.addColor(color);
		    gauge.addColor(new RGB("#ddd"));
		    gauge.setAngleField(provider);
		    gauge.setNeedle(needle);
		    gauge.setDonut(donut);
		    chart.addSeries(gauge);
		 
		    return chart;
		  }

	public Chart<AbonamentProxy> getChart(){
		return chart;
	}
}
