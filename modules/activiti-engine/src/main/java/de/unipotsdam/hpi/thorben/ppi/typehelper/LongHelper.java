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


public class LongHelper implements TypeHelper<Long> {

	public Long createZero() {
		return new Long(0L);
	}

	public Long add(Long summandA, Long summandB) {
		return summandA + summandB;
	}

	public Long divide(Long dividend, Long divisor) {
		return dividend/divisor;
	}

	public Long asType(Number n) {
		return new Long(n.longValue());
	}

	public Long createMaxValue() {
		return new Long(Long.MAX_VALUE);
	}

	public Long createMinValue() {
		return new Long(Long.MIN_VALUE);
	}

	public boolean greaterThan(Long valueA, Long valueB) {
		return valueA > valueB;
	}

	public boolean lowerThan(Long valueA, Long valueB) {
		return valueA < valueB;
	}

	public Long parseType(String s) {
		return Long.parseLong(s);
	}

}
