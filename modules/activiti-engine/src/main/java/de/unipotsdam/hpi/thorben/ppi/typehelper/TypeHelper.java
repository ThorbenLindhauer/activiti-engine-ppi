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

package de.unipotsdam.hpi.thorben.ppi.typehelper;

public interface TypeHelper<T extends Number> {

	T createZero();
	
	T add(T summandA, T summandB);
	
	T divide(T dividend, T divisor);
	
	T asType(Number n);
	
	T createMaxValue();
	
	T createMinValue();
	
	/**
	 * True, if valueA is greater than valueB.
	 * @param valueA
	 * @param valueB
	 * @return
	 */
	boolean greaterThan(T valueA, T valueB);
	
	/**
	 * True, if valueA lower than valueB.
	 * @param valueA
	 * @param valueB
	 * @return
	 */
	boolean lowerThan(T valueA, T valueB);
	
	T parseType(String s);
}
