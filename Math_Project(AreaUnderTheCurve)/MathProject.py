from sympy import *
import matplotlib.pyplot as plt
import numpy as np

# Menu and Options -------------------------------------------------------------------------------------------------
def menu():
    print("==========================//Menu//==========================")
    print("1| Calculate the area under the curve of a function and plot it")
    print("2| Calculate the area between two functions and plot it")
    print("============================================================")
    print("")
    print("What option would you like to choose?")
    option = int(input(" 1 | 2 | -> "))
    return option

while True:
    option = menu()
    # Code for Option 1 -------------------------------------------------------------------------------------------------
    if option == 1:
        # Define the Function
        f = input("Enter the function: ")
        a = float(input("Enter the lower limit: "))
        b = float(input("Enter the upper limit: "))
        n = int(input("Enter the number of segments: "))
        Lnc = (b - a) / n
        acum = 0
        for i in range(n):
            x = a
            fsuma = eval(f)
            area = Lnc * fsuma
            acum += area
            a += Lnc
        print('The area under the curve is: ', acum)
        
        # Plotting the function 
        x_vals = np.linspace(-10, 10, 100)
        y_vals = [eval(f) for x in x_vals]
        plt.plot(x_vals, y_vals)
        plt.title('Function Plot')
        plt.xlabel('x')
        plt.ylabel('f(x)')
        plt.axhline(0, color='black', lw=0.5, ls='--')
        plt.axvline(0, color='black', lw=0.5, ls='--')
        plt.grid()
        plt.show()

    # Code for Option 2 -------------------------------------------------------------------------------------------------        
    if option == 2:
        # Symbols
        x = symbols("x")
        # Define symbolic functions
        f1 = input("Enter the first function in terms of `x`: ")
        f2 = input("Enter the second function in terms of `x`: ")
        f1r = eval(f1)
        f2r = eval(f2)
        f3 = f1r - f2r 
        # Find the intersection points between the functions
        intersection_points = solve(f3, x)
        
        # Verify if the expressions are real numbers before converting to float
        real_points = [point.as_real_imag()[0] if point.is_real else float('nan') for point in intersection_points]
        
        # Calculate the midpoint
        pm = np.nanmean(real_points)
        
        # Evaluate the functions at the midpoint
        vf1 = f1r.subs(x, pm)
        vf2 = f2r.subs(x, pm)
        
        # Determine which function is above in the interval defined by the intersection points
        if vf1 > vf2:
            A = integrate(f3, (x, intersection_points[0], intersection_points[1]))
            print("")
            print("======================//Results//======================")
            print("The function above in the interval is: ", f1r)
            print("")
            print("The area between the curves is: ", float(A), "u^2")
            print("==========================================================")
        else:
            A = integrate(f3, (x, intersection_points[0], intersection_points[1]))
            print("")
            print("======================//Results//======================")
            print("The function above in the interval is: ", f2r)
            print("")
            print("The area between the curves is: ", float(A), "u^2")
            print("==========================================================")
        
        # Plotting using Matplotlib
        Vx = np.linspace(-10, 10, 100)
        Iyf1 = [float(f1r.subs(x, val)) for val in Vx]
        Iyf2 = [float(f2r.subs(x, val)) for val in Vx]
        
        plt.plot(Vx, Iyf1, label= "f(x) = {}".format(f1))
        plt.plot(Vx, Iyf2, label= "g(x) = {}".format(f2))
        plt.fill_between(Vx, Iyf1, Iyf2, where=(Iyf1 > Iyf2), interpolate=True, alpha=0.3, label='Area between curves')
        plt.legend()
        plt.xlabel("x")
        plt.ylabel("y")
        plt.title("Area between f(x) and g(x)")
        plt.axhline(0, color='black', lw=0.5, ls='--')
        plt.axvline(0, color='black', lw=0.5, ls='--')
        plt.grid()
        plt.show()

    # Code to Continue Options ------------------------------------------------------------------------------------------
    if option in [1, 2]:
        response = input("Do you want to continue in the menu? | Y | N | ----> ") 
        if response.upper() != "Y":
            print("==================================================")
            print("Thank you for using the program | End of process")
            print("==================================================")
            break
