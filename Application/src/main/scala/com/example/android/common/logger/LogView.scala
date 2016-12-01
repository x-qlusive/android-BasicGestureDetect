/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.common.logger

import android.app.Activity
import android.content.Context
import android.util._
import android.widget.TextView
import util.control.Breaks._

class LogView(context: Context
              , attrs: AttributeSet = null
              , defStyle: Int = 0) extends TextView(context, attrs, defStyle) with LogNode {

  def println(priority: Int, tag: String, msg: String, tr: Throwable) {
    var priorityStr: String = null
    priority match {
      case android.util.Log.VERBOSE =>
        priorityStr = "VERBOSE"
        break //todo: break is not supported
      case android.util.Log.DEBUG =>
        priorityStr = "DEBUG"
        break //todo: break is not supported
      case android.util.Log.INFO =>
        priorityStr = "INFO"
        break //todo: break is not supported
      case android.util.Log.WARN =>
        priorityStr = "WARN"
        break //todo: break is not supported
      case android.util.Log.ERROR =>
        priorityStr = "ERROR"
        break //todo: break is not supported
      case android.util.Log.ASSERT =>
        priorityStr = "ASSERT"
        break //todo: break is not supported
      case _ =>
        break //todo: break is not supported
    }
    var exceptionStr: String = null
    if (tr != null) {
      exceptionStr = android.util.Log.getStackTraceString(tr)
    }
    val outputBuilder: StringBuilder = new StringBuilder
    val delimiter: String = "\t"
    appendIfNotNull(outputBuilder, priorityStr, delimiter)
    appendIfNotNull(outputBuilder, tag, delimiter)
    appendIfNotNull(outputBuilder, msg, delimiter)
    appendIfNotNull(outputBuilder, exceptionStr, delimiter)
    getContext.asInstanceOf[Activity].runOnUiThread(new Thread(new Runnable() {
      def run():Unit = {
        appendToLog(outputBuilder.toString)
      }
    }))
    if (mNext != null) {
      mNext.println(priority, tag, msg, tr)
    }
  }

  def getNext: LogNode = {
    mNext
  }

  def setNext(node: LogNode) {
    mNext = node
  }

  private def appendIfNotNull(source: StringBuilder, addStr: String, delimiter: String): StringBuilder = {
    if (addStr != null) {
      val d = if (addStr.length == 0) "" else delimiter
      source.append(addStr).append(d)
    } else
      source
  }


  private[logger] var mNext: LogNode = null

  def appendToLog(s: String) {
    append("\n" + s)
  }
}
