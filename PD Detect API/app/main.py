# Necessary Imports
import flask
import numpy as np
import tensorflow as tf
import random
import os,io,sys
from flask import Flask, request, jsonify, json

# Base folders
base_folder = 'app/'
dataset_folder = 'dataset/'
models_folder = 'models/'

# Load models
PD_model = tf.keras.models.load_model(base_folder + models_folder + 'PD_Final_Model.h5')
HY_model = tf.keras.models.load_model(base_folder + models_folder + 'HY_Final_Model.h5')
FOG_model = tf.keras.models.load_model(base_folder + models_folder + 'FOG_Final_Model.h5')

# Load Numpy files
physionet_arr_shape = 19
physionet_loaded_arr = np.loadtxt(base_folder + dataset_folder + 'physionet_testX.out', delimiter=',')
physionet_data = physionet_loaded_arr.reshape(physionet_loaded_arr.shape[0], physionet_loaded_arr.shape[1] // physionet_arr_shape, physionet_arr_shape)
pd_class_data = np.loadtxt(base_folder + dataset_folder + 'physionet_testY.out', delimiter=',')
hy_class_data = np.loadtxt(base_folder + dataset_folder + 'physionet_testHY.out', delimiter=',')

daphnet_arr_shape = 9
daphnet_loaded_arr = np.loadtxt(base_folder + dataset_folder + 'daphnet_testX.out', delimiter=',')
daphnet_data = daphnet_loaded_arr.reshape(daphnet_loaded_arr.shape[0], daphnet_loaded_arr.shape[1] // daphnet_arr_shape, daphnet_arr_shape)
fog_class_data = np.loadtxt(base_folder + dataset_folder + 'daphnet_testY.out', delimiter=',')

# Shape
print("Physionet Data = ", physionet_data.shape)
print("PD Class = ", pd_class_data.shape)
print("HoehnYahr Class = ", hy_class_data.shape)

print("Daphnet Data = ", daphnet_data.shape)
print("FOG Class = ", fog_class_data.shape)

app = Flask(__name__)

@app.route("/")
def home():
  return "<h1>PD Detect API</h1>"

def physionet():       
  random_num = random.randint(0, physionet_data.shape[0])  
  print("[+] PHYSIONET DATA - random num, shape: ", random_num, physionet_data[random_num].shape)
  #print(physionet_data[random_num])
  return physionet_data[random_num], np.argmax(pd_class_data[random_num]), np.argmax(hy_class_data[random_num])

def daphnet():
  random_num = random.randint(0, daphnet_data.shape[0])  
  print("[+] DAPHNET DATA - random num, shape: ", random_num, daphnet_data[random_num].shape)
  #print(daphnet_data[random_num])
  return daphnet_data[random_num], np.argmax(fog_class_data[random_num])

def pdDetect(data):
  result = np.argmax(PD_model.predict(np.expand_dims(data, axis=0)))
  return result

def hyDetect(data):
  result = np.argmax(HY_model.predict(np.expand_dims(data, axis=0)))
  return result

def hySeverity(hy_class):
  '''
  Hoehn Yahr Scale
    0.0	- 0
    2.5	- 1
    2.0	- 2
    3.0	- 3
  '''
  if hy_class == 0:
    return "Healthy Control"
  elif hy_class == 1:
    return "2.5 severity"
  elif hy_class == 2:
    return "2 severity"
  elif hy_class == 3:
    return "3 severity"
  else:
    return "Invalid"

def fogDetect(data):
  result = np.argmax(FOG_model.predict(np.expand_dims(data, axis=0)))
  return result

@app.route("/finaldetect")
def finaldetect():
  pd_data, pd_class, hy_class = physionet()
  fog_data, fog_class = daphnet()
  
  pd_pred = pdDetect(pd_data)
  hy_pred = hyDetect(pd_data)
  fog_pred = fogDetect(fog_data)

  '''
    PD - 0 
    Control - 1
  ------------
    No Freeze - 0
    Freeze - 1
  '''

  print("[+] ORIGINAL CLASSES")
  if pd_class == 0:
    print("Parkinson's Disease")
  else:
    print("Healthy Control")
  
  if fog_class == 0:
    print("No Freeze")
  else:
    print("Freeze")
  
  print(hySeverity(hy_class))
  print("-"*5)
  
  predictions = dict()
  print("[+] PREDICTION")
  if pd_pred == 0 or fog_pred == 1:
    predictions['PD Result'] = "Parkinson's Disease"
    if fog_pred == 1:
      predictions['FOG Result'] = "Freezing of GAIT"
    else:
      predictions['FOG Result'] = "No Freezing of GAIT"
  else:
    predictions['PD Result'] = "Healthy Control"
    predictions['FOG Result'] = "No Freezing of GAIT"

  predictions['HY Result'] = hySeverity(hy_pred)
  print(predictions['PD Result'])
  print(predictions['FOG Result'])
  print(predictions['HY Result'])
  
  print("_"*10, "\n")

  try:
    return json.dumps({'result': predictions})
  except Exception as e:
    print(str(e))
    return json.dumps({'result': str(e)})

@app.after_request
def after_request(response):
  print("LOG: Setting CORS" , file = sys.stderr)
  response.headers.add('Access-Control-Allow-Origin', '*')
  response.headers.add('Access-Control-Allow-Headers', 'Content-Type,Authorization')
  response.headers.add('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE')  
  return response