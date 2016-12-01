/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.basicgesturedetect

import android.annotation.TargetApi
import android.os.Build
import android.view.GestureDetector
import android.view.MotionEvent
import com.example.android.common.logger.Log
import util.control.Breaks.break

object GestureListener {
  val TAG: String = "GestureListener"

  private def getTouchType(e: MotionEvent): String = {
    var touchTypeDescription: String = " "
    val touchType: Int = e.getToolType(0)
    touchType match {
      case MotionEvent.TOOL_TYPE_FINGER =>
        touchTypeDescription += "(finger)"
        break //todo: break is not supported
      case MotionEvent.TOOL_TYPE_STYLUS =>
        touchTypeDescription += "(stylus, "
        val stylusPressure: Float = e.getPressure
        touchTypeDescription += "pressure: " + stylusPressure
        if (Build.VERSION.SDK_INT >= 21) {
          touchTypeDescription += ", buttons pressed: " + getButtonsPressed(e)
        }
        touchTypeDescription += ")"
        break //todo: break is not supported
      case MotionEvent.TOOL_TYPE_ERASER =>
        touchTypeDescription += "(eraser)"
        break //todo: break is not supported
      case MotionEvent.TOOL_TYPE_MOUSE =>
        touchTypeDescription += "(mouse)"
        break //todo: break is not supported
      case _ =>
        touchTypeDescription += "(unknown tool)"
        break //todo: break is not supported
    }
    touchTypeDescription
  }

  @TargetApi(21) private def getButtonsPressed(e: MotionEvent): String = {
    var buttons: String = ""
    if (e.isButtonPressed(MotionEvent.BUTTON_PRIMARY)) {
      buttons += " primary"
    }
    if (e.isButtonPressed(MotionEvent.BUTTON_SECONDARY)) {
      buttons += " secondary"
    }
    if (e.isButtonPressed(MotionEvent.BUTTON_TERTIARY)) {
      buttons += " tertiary"
    }
    if (e.isButtonPressed(MotionEvent.BUTTON_BACK)) {
      buttons += " back"
    }
    if (e.isButtonPressed(MotionEvent.BUTTON_FORWARD)) {
      buttons += " forward"
    }
    if (buttons == "") {
      buttons = "none"
    }
    buttons
  }
}

class GestureListener extends GestureDetector.SimpleOnGestureListener {
  override def onSingleTapUp(e: MotionEvent): Boolean = {
    Log.i(GestureListener.TAG, "Single Tap Up" + GestureListener.getTouchType(e))
    false
  }

  override def onLongPress(e: MotionEvent) {
    Log.i(GestureListener.TAG, "Long Press" + GestureListener.getTouchType(e))
  }

  override def onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean = {
    Log.i(GestureListener.TAG, "Scroll" + GestureListener.getTouchType(e1))
    false
  }

  override def onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean = {
    Log.i(GestureListener.TAG, "Fling" + GestureListener.getTouchType(e1))
    false
  }

  override def onShowPress(e: MotionEvent) {
    Log.i(GestureListener.TAG, "Show Press" + GestureListener.getTouchType(e))
  }

  override def onDown(e: MotionEvent): Boolean = {
    Log.i(GestureListener.TAG, "Down" + GestureListener.getTouchType(e))
    false
  }

  override def onDoubleTap(e: MotionEvent): Boolean = {
    Log.i(GestureListener.TAG, "Double tap" + GestureListener.getTouchType(e))
    false
  }

  override def onDoubleTapEvent(e: MotionEvent): Boolean = {
    Log.i(GestureListener.TAG, "Event within double tap" + GestureListener.getTouchType(e))
    false
  }

  override def onSingleTapConfirmed(e: MotionEvent): Boolean = {
    Log.i(GestureListener.TAG, "Single tap confirmed" + GestureListener.getTouchType(e))
    false
  }
}
