/*
 * Copyright 2026 Patrik Karlström <patrik@trixon.se>.
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
package se.trixon.almond.util.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.awt.Color;
import java.io.IOException;
import se.trixon.almond.util.GraphicsHelper;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class AwtColorHexARGBDeserializer extends StdDeserializer<Color> {

    public AwtColorHexARGBDeserializer() {
        super(Color.class);
    }

    @Override
    public Color deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        return GraphicsHelper.colorFromAARRGGBB(parser.getText().trim());
    }

}
