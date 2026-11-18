/* 
 * Copyright 2023 Patrik Karlström.
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

import java.util.function.Consumer;
import javafx.event.ActionEvent;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionCheck;

/**
 * A checked Fx Action
 *
 * @author Patrik Karlström
 */
@ActionCheck
public class FxActionCheck extends Action {

    public FxActionCheck(String text) {
        super(text);
    }

    public FxActionCheck(Consumer<ActionEvent> eventHandler) {
        super(eventHandler);
    }

    public FxActionCheck(String text, Consumer<ActionEvent> eventHandler) {
        super(text, eventHandler);
    }
}
