package org.SGSJ.calc

import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import scalafx.application.JFXApp3
import scalafx.stage.Stage
import scalafx.stage.Stage.sfxStage2jfx

object Main extends JFXApp3 {
  override def start(): Unit =
    stage = JFXApp3.PrimaryStage()
    sfxStage2jfx(stage)
    val fxmlFile = loadFXML()
    val scene = Scene(fxmlFile)
    initComponents(stage, scene)
    stage.show()

  private def initComponents(stage: Stage, scene: Scene): Unit =
    val css = getClass.getResource("/stylesheets.css")
    scene.getStylesheets.add(css.toExternalForm)

    stage.setTitle("Calculator")
    stage.setResizable(false)
    stage.setScene(scene)

  private def loadFXML(): Parent =
    val url = getClass.getResource("/main.fxml")
    val loader = FXMLLoader()
    loader.setLocation(url)
    loader.load
}
