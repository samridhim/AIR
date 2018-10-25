import re, math
from collections import Counter

WORD = re.compile(r'\w+')

def get_cosine(vec1, vec2):
     intersection = set(vec1.keys()) & set(vec2.keys())
     numerator = sum([vec1[x] * vec2[x] for x in intersection])

     sum1 = sum([vec1[x]**2 for x in vec1.keys()])
     sum2 = sum([vec2[x]**2 for x in vec2.keys()])
     denominator = math.sqrt(sum1) * math.sqrt(sum2)

     if not denominator:
        return 0.0
     else:
        return float(numerator) / denominator

def text_to_vector(text):
     words = WORD.findall(text)
     return Counter(words)

questions  = ["Hi", "Bye", " I want to invest", "Max Stock?", "Print prices of available stocks"]
answers = ["Hello", "Cya", "Your Options are : ", "Your Max stock is:", "Okay here are the prices :"]
stocks = {}
price_stocks = {"S&P": 100, "Hongkong" : 200, "London" : 400, "Sensex" : 500, "Nifty50" : 200}
kk = True
while kk:
	question = raw_input("H:").lower()
	vector1 = text_to_vector(question)
	response = "B:"
	response_i = -1
	maxc=-1
	cosine = -1
	for i in range(len(questions)):
		q = str(questions[i]).lower()
		vector2 = text_to_vector(q)
		cosine = get_cosine(vector1, vector2)
		if maxc<cosine and cosine!=0:
			maxc = cosine 
			response_i = i	
	if(response_i == -1):
		print("Sorry I do not understand")
		exit()
	print response + answers[response_i]
	response = answers[response_i].lower()
	if "options" in response:
		print price_stocks
		vector3 = text_to_vector(q)
		qq = raw_input("Which option do you choose?(Write name of stock) : ")
		un = raw_input("How many units of stock do you want to buy?")
		print "You are buying " + str(qq) + " & units: " + str(un)
		if qq in stocks:		
			stocks[qq] = int(stocks[qq]) + int(un);
		else:
			stocks[qq] = un;	
		print stocks
	elif "max" in response:
		maxs = 0	
		maxd = " "	
		for d in stocks:
			if(stocks[d] >maxs):
				maxs = stocks[d]
				maxd = d
		print "Stock: " + str(maxd), " Shares: " +str(maxs) + " Total Price : " + str(int(maxs)*price_stocks[maxd])
	elif "prices" in response:
		for p in price_stocks:
			print (p, price_stocks[p])
	elif(answers[response_i] == "Cya"):
		exit()	
