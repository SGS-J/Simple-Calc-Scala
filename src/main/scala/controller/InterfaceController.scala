package org.SGSJ.calc
package controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.{Button, Label, TextField}

import java.util.{Optional, OptionalDouble}

class InterfaceController {
  @FXML var inputNumbers: TextField = _
  @FXML var signLabel: Label = _

  private var num1 = 0D
  private var num2 = 0D
  private var inputText = ""
  private var result = 0D
  private var resultDefined = false
  private var isTypingNum2 = false
  private var signTimesPressed = 0
  private var signState = false
  private var sign = ""

  def handlePressNumber(e: ActionEvent): Unit =
    val btn = e.getSource.asInstanceOf[Button]
    val btnTxt = btn.getText
    inputText += btnTxt
    if btnTxt == "." then
      replacePointDecimal()
    if isTypingNum2 then
      num2 = inputText.toDouble
    else num1 = inputText.toDouble
    signState = false
    inputNumbers.setText(inputText)

  def handleUndo(): Unit =
    if inputText.nonEmpty then
      inputText = inputText.substring(0, inputText.length - 1)
      if isTypingNum2 then num2 = inputText.toDouble
      else num1 = inputText.toDouble
      inputNumbers.setText(inputText)
    signState = false

  def handleReset(): Unit =
    reset()
    inputNumbers.setText(inputText)

  def handleSign(e: ActionEvent): Unit =
    val btn = e.getSource.asInstanceOf[Button]
    if !signState then
      signTimesPressed += 1
      if signTimesPressed >= 2 then
        setResultNumbers()
        operate()
        inputResult()
      isTypingNum2 = !isTypingNum2
    sign = btn.getText
    signLabel.setText(sign)
    inputText = ""
    signState = true

  def handleEqual(): Unit =
    setResultNumbers()
    operate()
    inputResult()
    reset()

  private def replacePointDecimal(): Unit =
    if inputText.matches("(.*\\..*){2,}") then
      val pointIdx = inputText.lastIndexOf(".")
      val str1 = inputText.substring(0, pointIdx)
      val str2 = inputText.substring(pointIdx + 1, inputText.length)
      inputText = str1 + str2

  private def inputResult(): Unit =
    inputText = result.toString
    inputNumbers.setText(inputText)

  private def setResultNumbers(): Unit =
    if !resultDefined then
      result = num1
      resultDefined = true
    if isTypingNum2 then num1 = result
    else
      num2 = num1
      num1 = result

  private def reset(): Unit =
    inputText = ""
    isTypingNum2 = false
    resultDefined = false
    signState = false
    signTimesPressed = 0
    num1 = 0
    num2 = 0
    result = 0
    signLabel.setText("")

  private def operate(): Unit =
    sign match {
      case "+" =>
        result = num1 + num2
      case "-" =>
        result = num1 - num2
      case "X" =>
        result = num1 * num2
      case "/" =>
        result = num1 / num2
    }
}
