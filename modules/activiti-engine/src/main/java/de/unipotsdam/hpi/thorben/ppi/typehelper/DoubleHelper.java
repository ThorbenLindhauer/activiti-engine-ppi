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


public class DoubleHelper implements TypeHelper<Double> {

	public Double createZero() {
		return new Double(0);
	}

	public Double add(Double summandA, Double summandB) {
		return summandA + summandB;
	}

	public Double divide(Double dividend, Double divisor) {
		return dividend / divisor;
	}

	public Double asType(Number n) {
		return new Double(n.doubleValue());
	}

	public Double createMaxValue() {
		return new Double(Double.MAX_VALUE);
	}

	public Double createMinValue() {
		return new Double(-Double.MAX_VALUE);
	}

	public boolean greaterThan(Double valueA, Double valueB) {
		return valueA > valueB;
	}

	public boolean lowerThan(Double valueA, Double valueB) {
		return valueA < valueB;
	}

	public Double parseType(String s) {
		return Double.parseDouble(s);
	}

}
