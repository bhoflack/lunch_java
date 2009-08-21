/*
 *  Copyright 2009 kry.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package com.melexis;

import com.melexis.repository.ProductRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 *
 * @author kry
 */
public class DetachableModelOrder extends LoadableDetachableModel<ModelOrder> {

    private final ProductRepository productRepository;
    private final int id;

    public DetachableModelOrder(ProductRepository productRepository, int id)
    {
        this.id = id;
        this.productRepository = productRepository;
    }

    @Override
    protected ModelOrder load() {
        Product p;
        
        try {
            p = productRepository.findById(id);

            return new ModelOrder(p);
        } catch (ProductNotFoundException ex) {
            Logger.getLogger(DetachableModelOrder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
