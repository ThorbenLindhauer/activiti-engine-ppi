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


public class IntegerHelper implements TypeHelper<Integer> {

	public Integer createZero() {
		return new Integer(0);
	}

	public Integer add(Integer summandA, Integer summandB) {
		return summandA + summandB;
	}

	public Integer divide(Integer dividend, Integer divisor) {
		return dividend/divisor;
	}

	public Integer asType(Number n) {
		return new Integer(n.intValue());
	}

	public Integer createMaxValue() {
		return new Integer(Integer.MAX_VALUE);
	}

	public Integer createMinValue() {
		return new Integer(Integer.MIN_VALUE);
	}

	public boolean greaterThan(Integer valueA, Integer valueB) {
		return valueA > valueB;
	}

	public boolean lowerThan(Integer valueA, Integer valueB) {
		return valueA < valueB;
	}

	public Integer parseType(String s) {
		return Integer.parseInt(s);
	}

}
