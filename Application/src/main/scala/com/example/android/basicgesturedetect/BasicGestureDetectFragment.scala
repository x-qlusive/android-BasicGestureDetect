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
package com.example.android.basicgesturedetect

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.GestureDetector
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import com.example.android.common.logger.LogFragment

class BasicGestureDetectFragment extends Fragment {
  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override def onActivityCreated(savedInstanceState: Bundle) {
    super.onActivityCreated(savedInstanceState)
    val gestureView: View = getActivity.findViewById(R.id.sample_output)
    gestureView.setClickable(true)
    gestureView.setFocusable(true)
    val gestureListener: GestureDetector.SimpleOnGestureListener = new GestureListener
    val gd: GestureDetector = new GestureDetector(getActivity, gestureListener)
    gestureView.setOnTouchListener(new View.OnTouchListener() {
      def onTouch(view: View, motionEvent: MotionEvent): Boolean = {
        gd.onTouchEvent(motionEvent)
        false
      }
    })
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    if (item.getItemId == R.id.sample_action) {
      clearLog
    }
    true
  }

  def clearLog():Unit = {
    val logFragment: LogFragment = getActivity.getSupportFragmentManager.findFragmentById(R.id.log_fragment).asInstanceOf[LogFragment]
    logFragment.getLogView.setText("")
  }
}
