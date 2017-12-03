clear;

% read in data
file = 'example1.dat';
E = csvread(['../data/' file]);
disp('------- Data read-in done! -------');
disp(['Data file name: ' file]);

% initial parameters
k = 4;
sigma_rng = 0.5 : 0.5 : 10;
dim = size(E, 2);
min_sumd = inf;

for sigma = sigma_rng
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
    [idx, C, sumd] = kmeans(Y, k);
    
    if sum(sumd) < min_sumd
        min_sumd = min(min_sumd, sum(sumd));
        opt_sigma = sigma;
        opt_orders = orders;
        opt_idx = idx;
    end
end

disp('------- Grid search done! -------');
disp(['Number of clusters: ' num2str(k)]);
disp(['Optimal sigma: ' num2str(opt_sigma)]);

% assign original points according to clustering results by k-means
[~, r_orders] = sort(opt_orders, 'descend');
clusters = opt_idx(r_orders);

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
