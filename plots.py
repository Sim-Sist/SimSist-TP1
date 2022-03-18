from matplotlib import pyplot as plt

particles = [0, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000]

cim_particles_t = [
    0.605351, 0.993635, 1.031828, 1.738321, 3.023232, 3.998633, 2.754941,
    3.398260, 2.843955, 2.973367, 3.698331
]

grid_sizes = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]

cim_grid_sizes_t = [
    46.079568, 30.905227, 18.539627, 12.072811, 9.763108, 6.972020, 4.347201,
    3.965385, 3.703457, 3.961269, 3.730032, 3.410255, 3.142535
]

bf_particles_t = [
    0.058997, 0.849700, 1.477874, 1.589864, 1.787818, 2.740665, 4.087185,
    5.753728, 7.535577, 9.614238, 12.063900
]

bf_grid_sizes_t = [
    11.823171, 11.885860, 12.131268, 8.281475, 13.665150, 12.813952, 13.465221,
    12.501088, 12.270284, 12.359005, 12.168936, 12.132586, 11.190812
]

fig, ax = plt.subplots(2, 2, figsize=(12, 12))

ax[0, 0].plot(particles, cim_particles_t, '-o')
ax[0, 0].set_title("Tiempo transcurrido en función de cantidad de partículas")
ax[0, 0].set_xlabel('partículas')
ax[0, 0].set_ylabel('tiempo transcurrido')

ax[1, 0].set_title("Tiempo transcurrido en función de M")
ax[1, 0].set_xlabel('tamaño de la grilla')

# ax = plt.axes()

# ax.set_title()

ax[1, 0].plot(grid_sizes, cim_grid_sizes_t, '-o')

ax[0, 1].plot(particles, bf_particles_t, '-o')
ax[0, 1].set_title("Fuerza Bruta")
# ax[1, 0].set_xlabel('partículas')
ax[0, 1].set_ylabel('tiempo transcurrido')

plt.show()