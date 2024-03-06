/*
 * Copyright 2024 Patrik Karlström <patrik@trixon.se>.
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
package se.trixon.almond.nbp.output;

import org.openide.windows.OutputEvent;
import org.openide.windows.OutputListener;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class OutputAdapter implements OutputListener {

    @Override
    public void outputLineAction(OutputEvent ev) {
    }

    @Override
    public void outputLineCleared(OutputEvent ev) {
    }

    @Override
    public void outputLineSelected(OutputEvent ev) {
    }
}