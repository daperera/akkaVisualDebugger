package com.akkaVisualizor.javaFX.view;

import com.akkaVisualizor.Context;
import com.akkaVisualizor.visualModel.VisualChannel;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class ChannelView extends Line {
	private VisualChannel visualChannel;

	public ChannelView(Context context, VisualChannel visualChannel) {
		// init variables
		this.visualChannel = visualChannel;

		startXProperty().bind(visualChannel.getSource().getXProperty());
		startYProperty().bind(visualChannel.getSource().getYProperty());
		endXProperty().bind(visualChannel.getTarget().getXProperty());
		endYProperty().bind(visualChannel.getTarget().getYProperty());



		// init style
		setStrokeWidth(2);
		setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
		setStrokeLineCap(StrokeLineCap.BUTT);
		getStrokeDashArray().setAll(10.0, 5.0);
		DropShadow borderGlow = new DropShadow();
		borderGlow.setColor(Color.BLUE);
		borderGlow.setOffsetX(0f);
		borderGlow.setOffsetY(0f);

		// init controller
		setOnMousePressed(e -> context.getGlobalMouseController().onMousePressed(this, e));

		// add border glow effect when selected
		visualChannel.getSelectedProperty().addListener((ChangeListener<Boolean>) (o, oldVal,  newVal) -> { 
			if(newVal) 
				setEffect(borderGlow); 
			else
				setEffect(null);
		});


		// delete itself when the actor is set deleted
		visualChannel.getDeletedProperty().addListener((ChangeListener<Boolean>) (o, oldVal,  newVal) -> { 
			if(newVal) {
				// use Platform  in order to avoid throwing IllegalStateException by running from a non-JavaFX thread
				Platform.runLater(() -> ((Pane) ChannelView.this.getParent()).getChildren().remove(ChannelView.this));
			}
		});
	}

	public VisualChannel getModel() {
		return visualChannel;
	}
	
}
