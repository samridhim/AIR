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

questions  = ["Hi", "Bye", " I Need help with investment", "Budget is 20000","Budget is 30000", "Budget is 50000", "What are my stock options?", "Max Stock Option?"]
answers = ["Hello", "Cya", " Sure. What is your budget?", "Okay you have 3 options : S&P, Nifty50, Sensex", "Okay you have 3 options : 1. S&P, 2. Hongkong, 3. Sensex", "Okay you have 3 options : 1. S&P, 2. London, 3. Sensex","Your Options are : ", "Your Max option is:"]
stocks = {}
kk = True
while kk:
	question = raw_input("H:").lower()
	vector1 = text_to_vector(question)
	response = "B:"
	response_i = 0
	maxc=-1
	for i in range(len(questions)):
		q = str(questions[i]).lower()
		vector2 = text_to_vector(q)
		cosine = get_cosine(vector1, vector2)
		if maxc<=cosine:
			maxc = cosine 
			response_i = i
	print response + answers[response_i]
	if "options" in answers[response_i]:
		vector3 = text_to_vector(q)
		qq = raw_input("Which option do you choose?(Write name of stock) : ")
		un = raw_input("How many units of stock do you want to buy?")
		print "You are buying " + str(qq) + " & units: " + str(un)
		stocks[qq] = un	
	if "Options" in answers[response_i]:
		for d in stocks:
			print "Stock: " + d, "Shares: " + stocks[d]
	if "Max" in answers[response_i]:
			maxs = 0	
			maxd = " "	
			for d in stocks:
				if(stocks[d] >maxs):
					stocks[d] = maxs
					maxd = d
			print "Stock: " + str(maxd), "Shares: " +str(maxs)
	if(answers[response_i] == "Cya"):
		exit()	
