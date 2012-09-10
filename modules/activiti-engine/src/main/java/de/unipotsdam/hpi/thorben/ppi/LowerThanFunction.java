/* Copyright 2012 Thorben Lindhauer
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

package de.unipotsdam.hpi.thorben.ppi;

import de.unipotsdam.hpi.thorben.ppi.typehelper.TypeHelper;

public class LowerThanFunction<N extends Number> extends AbstractTargetFunction<N> {

	public LowerThanFunction(TypeHelper<N> typeHelper) {
		super(typeHelper);
	}

	protected boolean applySpecific(N operator1, N operator2) {
		return helper.lowerThan(operator1, operator2);
	}
}
