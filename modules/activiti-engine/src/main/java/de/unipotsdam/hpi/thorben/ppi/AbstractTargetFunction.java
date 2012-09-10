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

import de.unipotsdam.hpi.thorben.ppi.measure.process.TargetFunction;
import de.unipotsdam.hpi.thorben.ppi.typehelper.TypeHelper;

public abstract class AbstractTargetFunction<N extends Number> implements
		TargetFunction {
	
	protected TypeHelper<N> helper;
	
	public AbstractTargetFunction(TypeHelper<N> typeHelper) {
		this.helper = typeHelper;
	}

	public boolean apply(Number operator1, Number operator2) {
		return applySpecific(helper.asType(operator1), helper.asType(operator2));
	}

	protected abstract boolean applySpecific(N operator1, N operator2);
}
