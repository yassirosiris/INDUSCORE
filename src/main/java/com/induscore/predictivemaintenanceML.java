package com.induscore;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.Attribute;

public class predictivemaintenanceML { // Riguelt l'esm lel Standard (P majuscule)
    private Classifier model; 

    public predictivemaintenanceML(Classifier model) {
        this.model = model;
    }

    public boolean predictFailure(double temperature, double vibration) {
        try {
            // 1. Définition des attributs
            FastVector<Attribute> attributes = new FastVector<>(2);
            attributes.addElement(new Attribute("temperature"));
            attributes.addElement(new Attribute("vibration"));

            // 2. Création du Dataset (Lazma l'Weka bech ya3ref el structure)
            Instances data = new Instances("SensorData", attributes, 0);
            
            // 3. Création de l'instance avec les valeurs réelles
            Instance instance = new DenseInstance(1.0, new double[]{temperature, vibration});
            
            // --- EL FAZA EL MOUHIMA ---
            instance.setDataset(data); 
            // ---------------------------

            // 4. Classification
            double prediction = model.classifyInstance(instance);
            
            // Log sghir bech tchouf chnouwa sary fil Console
            System.out.println("ML Prediction: " + prediction + " [Temp: " + temperature + ", Vib: " + vibration + "]");
            
            return prediction == 1.0; 

        } catch (Exception e) {
            System.err.println("Erreur ML: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}