# Human Biomechanic Analysis
# Deep Learning Models for the Early Detection of Parkinson’s Disease using the motor-based symptoms.

## Abstract
The old age population is commonly affected with gait and balance disorders such as Parkinson’s disease (PD). PD is a progressive neurodegenerative movement disorder caused by the degeneration of substantia nigra dopaminergic neurons present in basal ganglia. Parkinson’s Disease (PD) is a disorder of the central nervous system that affects movement, often including tremors. 

We have designed a system that uses **deep learning to detect Parkinson’s Disease at an earlier stage**. Using the motor-based inputs we have developed deep learning models which detects motor-based symptoms in the subject’s body to subsequently detect the onset of Parkinson’s disease and also the severity of the Parkinson’s Disease based on the Hoehn Yahr Severity Scale.

---

## Motivation
According to the Parkinson Disease Foundation, one million Americans are living with Parkinson disease, and approximately 60,000 Americans are diagnosed with Parkinson disease each year. Similarly, 1.2 million Europeans suffer from it, and this number is forecasted to double by 2030.

The **early detection of Parkinson’s Disease** is a clinical challenge for the scientific community. Currently, no cure is available for the disease or the symptoms; therefore, movement therapy is important to delay the loss of motor function. Patients generally are clearly diagnosed with PD at the advanced stage; moreover, any neuroprotective therapy initiated at such a late stage may have fewer substantial effects on the disease progression. The Deep Learning Models can help in the early identification of Parkinson's Disease using the motor and non-motor symptoms, so that patients can receive the proper treatment and advice regarding care.

---

## Datasets
### [**Physionet Dataset**](https://physionet.org/content/gaitpdb/1.0.0/)
  - This database contains measures of gait from: 
    -	93 patients with PD (mean age: 66.3 years; 63% men)
    -	73 healthy controls (mean age: 66.3 years; 55% men)
  -	The database includes the vertical ground reaction force records of subjects as they walked at their usual, self-selected pace for approximately 2 minutes on level ground.
  -	The output of each of these 16 sensors has been digitized and recorded at **100 samples per second**, and the records also include two signals that reflect the sum of the 8 sensor outputs for each foot. 

![Position of 16 sensors](https://user-images.githubusercontent.com/53406309/154796873-dace410a-3350-4177-9134-97871521e2de.png)

### [**Daphnet Dataset**](https://archive.ics.uci.edu/ml/datasets/Daphnet+Freezing+of+Gait)
The dataset comprises of recordings of 3D acceleration at 64 Hz from 3 acceleration sensors. The sensors are placed at the ankle (shank), on the thigh, and on the hip. The dataset was recorded in the lab with emphasis on generating many freeze events. Users performed three kinds of tasks: straight line walking, walking with numerous turns, and finally a more realistic activity of daily living (ADL) task, where users went into different rooms while fetching coffee, opening doors, etc. The meaning of the annotations of the samples are as follows:
•	0: not part of the experiment. For instance, the sensors are installed on the user or the user is performing activities unrelated to the experimental protocol, such as debriefing.
•	1: experiment, no freeze (can be any of stand, walk, turn)
•	2: freeze

![Position of 3 wearable wireless acceleration sensors](https://user-images.githubusercontent.com/53406309/154796892-aa350d45-9b3a-47ef-bb8b-5e7245087b17.png)

---

## System Architecture
![image](https://user-images.githubusercontent.com/53406309/154796525-48cda3ab-e596-475f-8bd5-569ec2f44841.png)

1. **Input from 8 force sensors under each foot**
    - The 8 force sensors are placed under each foot (16 force sensors) that measure the vertical ground reaction force at 100 Hz.

1.	**Input from 3 acceleration sensors**
    - The 3 acceleration sensors are placed at Trunk, Upper Leg (Thigh) and Ankle (Shank) that measure the acceleration in 3 channels (x, y and z axis) at 64 Hz.

1.	**CNN for Detection of Parkinson’s Disease**
    - Based on the data from 16 force sensors, Convolutional Neural Network is used for the Detection of Parkinson’s Disease.

1.	**CNN for Classification of Parkinson’s Disease Severity**
    - Based on the data from 16 force sensors, Convolutional Neural Network is used for the Classification of Parkinson’s Disease Severity.

1.	**CNN for Detection of Freezing of GAIT**
    - Based on the data from 3 acceleration sensors, Convolutional Neural Network is used for the Detection of Freezing of GAIT.

1.	**Final Output**
    - The Detection of Parkinson’s Disease model and Freezing GAIT models are combined together using voting method to produce the Final Output. 
    - The Classification model produces the Parkinson’s Disease Severity Based on the Hoehn Yahr Scale.


### Parkinson's Disease Detection Model
Based on the data from 16 force sensors, the Parkinson’s Disease Detection model is shown below. The output of this model is the detection of the presence of PD in the patients.

![PD Detection Model](https://user-images.githubusercontent.com/53406309/154798470-27f92033-a713-44c5-b55b-c51190ea5c19.png)

### Parkinson's Disease Severity Classification Model
Based on the data from 16 force sensors, the Parkinson’s Disease Severity Classification model is shown below. The output of this model is the classification of the severity of PD in the patients based on the Hoehn Yahr (HY) scale.

![PD Serverity Classification Model](https://user-images.githubusercontent.com/53406309/154798491-00ad630a-2a75-440b-a8b2-f51d9910b109.png)

### Freezing of Gait Detection Model
Based on the data from 3 acceleration sensors, the Freezing of GAIT Detection Model is shown below. The output of this model is the detection of the presence of Freezing of GAIT (FOG) in the patients.

![FOG Detection Model](https://user-images.githubusercontent.com/53406309/154798532-0e28ba5f-2b79-44cb-aa6a-70933e07b650.png)

### Final Prediction by Voting
- The Parkinson’s Disease Detection Model and Freezing of Gait Detection Model are combined together for the Detection of Parkinson’s Disease using the voting technique. 
- The severity of Parkinson's Disease is given by the Parkinson's Disease Severity Classification Model based on the Hoehn Yahr Severity Scale.
- Since the final output is based on two models, the combined final prediction will be detecting the presence of Parkinson’s Disease more accurately.

---

## PD DETECT - Android Application
- PD Detect is a mobile application developed for the Detection of Parkinson’s Disease and for assessing the severity of the Parkinson’s Disease based on Hoehn Yahr Scale and few survey questions. 
- This application detects the Parkinson's disease using the output from 3 accelerometers placed at the ankle (shank), on the thigh, and on the hip & 16 sensors measuring force (in Newton) placed at the feet. The data is stored and processed in cloud.

