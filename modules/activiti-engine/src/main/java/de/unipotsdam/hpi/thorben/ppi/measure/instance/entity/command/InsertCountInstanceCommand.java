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

package de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.command;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import de.unipotsdam.hpi.thorben.ppi.measure.instance.entity.CountMeasureInstance;


public class InsertCountInstanceCommand implements Command<Void> {

	private CountMeasureInstance value;
	
	public InsertCountInstanceCommand(CountMeasureInstance value) {
		this.value = value;
	}

	public Void execute(CommandContext commandContext) {
		commandContext.getBaseMeasureManager().insertCountMeasureInstance(value);
		return null;
	}

}
