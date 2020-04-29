import random


class SimplePlotGenerator():
    
    def __init__(self):

        self.lst = []


    def generate(self):
        if self.lst == []:
            return 'Something happnes'
        else:
            print('{}, a {} {}, must {} the {} {}, {}.'.format(self.lst[0],self.lst[1],self.lst[2],self.lst[3],self.lst[4],self.lst[5],self.lst[6]))



    #def __repr__(self):
        #return "SimplePlotGenerator({})".format(self.lst)

    
class RandomPlotGenerator(SimplePlotGenerator):
    
    def __init__(self):

        #read file
        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_names.txt', 'r')
        file_plot_names=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_adjectives.txt', 'r')
        file_plot_adjectives=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_profesions.txt', 'r')
        file_plot_profesions=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_verbs.txt', 'r')
        file_plot_verbs=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_adjectives_evil.txt', 'r')
        file_plot_adjectives_evil=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_villian_job.txt', 'r')
        file_plot_villian_job=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_villains.txt', 'r')
        file_plot_villains=infile.readlines()
        infile.close()        

        self.lst = []
        #random.choice(a)
        part1 = random.choice(file_plot_names)
        self.lst.append(part1.strip('\n'))
        
        part2 = random.choice(file_plot_adjectives)
        self.lst.append(part2.strip('\n'))
        
        part3 = random.choice(file_plot_profesions)
        self.lst.append(part3.strip('\n').strip(' '))
        
        part4 = random.choice(file_plot_verbs)        
        self.lst.append(part4.strip('\n'))
        
        part5 = random.choice(file_plot_adjectives_evil)
        self.lst.append(part5.strip('\n'))
        
        part6 = random.choice(file_plot_villian_job)
        self.lst.append(part6.strip('\n'))
        
        part7 = random.choice(file_plot_villains)
        self.lst.append(part7.strip('\n'))
        
        

    #def __repr__(self):
        #return "SimplePlotGenerator({})".format(self.lst)

class InteractivePlotGenerator(SimplePlotGenerator):
    
    def __init__(self):

        #read file
        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_names.txt', 'r')
        file_plot_names=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_adjectives.txt', 'r')
        file_plot_adjectives=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_profesions.txt', 'r')
        file_plot_profesions=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_verbs.txt', 'r')
        file_plot_verbs=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_adjectives_evil.txt', 'r')
        file_plot_adjectives_evil=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_villian_job.txt', 'r')
        file_plot_villian_job=infile.readlines()
        infile.close()

        infile = open(r'C:\Users\louise\Desktop\python\DSC430HW8\plot_villains.txt', 'r')
        file_plot_villains=infile.readlines()
        infile.close()  


        self.lst = []
        #random.choice(a)
        part1 = random.sample(file_plot_names,5)
        
        part1choice = [] 
        for i in part1:
            i = i.strip('\n')
            part1choice.append(i)
        print(part1choice)
        
        part1 = input("please select the one name for the choices above: ")
        self.lst.append(part1)
        
        
        part2 = random.sample(file_plot_adjectives,5)

        part2choice = [] 
        for i in part2:
            i = i.strip('\n')
            part2choice.append(i)
        print(part2choice)
        
        part2 = input("please select the one adjective for the choices above: ")
        self.lst.append(part2)
        
        
        part3 = random.sample(file_plot_profesions,5)
        
        part3choice = [] 
        for i in part3:
            i = i.strip('\n').strip(' ')
            part3choice.append(i)
        print(part3choice)
        
        part3 = input("please select the one profesion for the choices above: ")
        self.lst.append(part3)
        
        
        part4 = random.sample(file_plot_verbs,5)        
        
        part4choice = [] 
        for i in part4:
            i = i.strip('\n')
            part4choice.append(i)
        print(part4choice)
        
        part4 = input("please select the one verb for the choices above: ")
        self.lst.append(part4)
        
        
        part5 = random.sample(file_plot_adjectives_evil,5)
        
        part5choice = [] 
        for i in part5:
            i = i.strip('\n')
            part5choice.append(i)
        print(part5choice)
        
        part5 = input("please select the one adjective_evil for the choices above: ")
        self.lst.append(part5)
        
        
        part6 = random.sample(file_plot_villian_job,5)
        
        part6choice = [] 
        for i in part6:
            i = i.strip('\n')
            part6choice.append(i)
        print(part6choice)
        
        part6 = input("please select the one villian_job for the choices above: ")
        self.lst.append(part6)
        
        
        part7 = random.sample(file_plot_villains,5)
        
        part7choice = [] 
        for i in part7:
            i = i.strip('\n')
            part7choice.append(i)
        print(part7choice)
        
        part7 = input("please select the one villain for the choices above: ")
        self.lst.append(part7)





















