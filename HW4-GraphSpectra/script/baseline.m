clear;

% read in data
file = 'example2.dat';
E = csvread(['../data/' file]);
disp('------- Data read-in done! -------');
disp(['Data file name: ' file]);

% initial parameters
sigma = 1.5;
dim = size(E, 2);
k = 4;

% form affinity matrix A
pair_dist = squareform(pdist(E));
A = exp(-(pair_dist.^2)./(2*sigma^2));
A = A - diag(ones(size(A, 1), 1));      % to keep Aii = 0

% define D and construct matrix L
D = diag(sum(A, 2));
L = single(D^(-0.5)*A*D^(-0.5));

% find the k largest eigenvectors of L
[v, d] = eig(L);
[eig_val, orders] = sort(diag(d), 'descend');
eig_vec = v(:, orders);
X = eig_vec(:, 1 : k);

% renormalize X
Y = normr(X);

% cluster rows of Y by k-means
[idx, C] = kmeans(Y, k);

% assign original points according to clustering results by k-means
[~, r_orders] = sort(orders, 'descend');
clusters = idx(r_orders);

disp('------- Clustering done! -------');

% plot results
e1 = E(:, 1);
e2 = E(:, 2);
if dim == 3
    e3 = E(:, 3);
end
figure;
for i = 1 : k
    color = rand(1, 3);
    if dim == 2
        scatter(e1(clusters == i), e2(clusters == i), [], color, 'filled');
    elseif dim == 3
        scatter3(e1(clusters == i), e2(clusters == i), e3(clusters == i), [], color, 'filled');
    else
        error('too high dimension to visualize data!');
    end
    hold on;
end
title([file ' ,' num2str(k) ' clusters']);
