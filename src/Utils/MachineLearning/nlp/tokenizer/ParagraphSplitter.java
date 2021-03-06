/*******************************************************************************
 * Copyright (c) 2010 Haifeng Li
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
 *******************************************************************************/

package Utils.MachineLearning.nlp.tokenizer;

/**
 * A paragraph splitter segments text into paragraphs. A paragraph is a
 * coherent block of text, such as a group of related sentences that develop
 * a single topic or a coherent part of a larger topic. 
 *
 * @author Haifeng Li
 */
public interface ParagraphSplitter {
    /**
     * Split text into sentences.
     */
    public String[] split(String text);
}
