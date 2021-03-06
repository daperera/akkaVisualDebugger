package com.akkaVisualizor.javaFX.pane;

import com.akkaVisualizor.javaFX.view.ActorTypeListView;
import com.akkaVisualizor.utils.Context;
import com.akkaVisualizor.visualModel.visual.VisualActorType;

import javafx.scene.control.ListView;

public class ActorTypePane extends ListView<VisualActorType> {

	public ActorTypePane(Context context) {
		setCellFactory(param -> new ActorTypeListView(context));
		setItems(context.getGlobalModel().getVisualActorTypeList().get());
	}
	
}
