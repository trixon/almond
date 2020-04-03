/*
 * Copyright 2019 Patrik Karlström.
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
package se.trixon.almond.util.fx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author Patrik Karlström
 */
public class DelayedResetRunner {

    private long mStart = System.currentTimeMillis();
    private Timeline mTimeline;

    public DelayedResetRunner(int delay, Runnable r) {
        mTimeline = new Timeline(new KeyFrame(Duration.millis(delay), ae -> {
            if (System.currentTimeMillis() - mStart >= delay) {
                r.run();
                mTimeline.stop();
            }
        }));

        mTimeline.setCycleCount(Animation.INDEFINITE);
        mTimeline.play();
    }

    public void reset() {
        mStart = System.currentTimeMillis();
        mTimeline.playFromStart();
    }
}
